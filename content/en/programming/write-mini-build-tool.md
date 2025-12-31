---
title: How to Write a Mini Build Tool?
description: Build tools, make, maven, gradle, sbt, mill, java, scala, groovy, kotlin
publish_date: 2025-12-31 17:00
---

# How to Write a Mini Build Tool?

You might have worked with `npm` and similar tools, those are *not build tools*.  
At least not by a common understanding. Those fall into a category of "task runners".
The biggest difference being they don't have a notion of a *task dependencies graph*.  
For example, if you run `npm run start`, that won't automatically "know" to run "npm install" before that.
This is what build tools *do know*, and some more!

And that's why build tools are used and loved! (ok maybe not loved that much..)

Some build tools call "task" a "target", or some other name..  
Just keep in mind that terminology can differ.  
Here by task we just mean "something we can run"..

## Project

By a project I mean "the root", root of your git repo, where it all starts.

## Modules Graph

Projects usually have multiple modules, which can depend on each other.  
Thus they form a graph.

For example you could have a `common` module, and `backend` module depends on it.
If you run `compile` on `backend`, you expect the `common` module to be compiled first!
This is what we call "transitivity", the `common` module's `compile` task is invoked "transitively".

Sometimes the modules can even point to the *same source files*!  
For example you could have a cross compiled scala 2/3 project.  
Or you could have a multi-release java JAR.

In Scala you could represent modules with something like this:
```scala
trait Module {
  def tpe: String
  def id: String
  def moduleDeps: Seq[String] // module ids
}

case class JavaModule(
    id: String,
    moduleDeps: Seq[String]
) extends Module {
  def tpe: String = "java"
}
```


## Tasks Graph

The "tasks dependencies graph" can be represented in many ways:
- [maven](https://maven.apache.org/) uses XML in a *declarative way*, you can't easily define a custom task! (unless you write a plugin of course)
- [make](https://www.gnu.org/software/make/) uses specialized `Makefile` syntax, where you can put dependencies in brackets, e.g. `mytarget: [prerequisites...]`
- [ant](https://ant.apache.org/) uses XML instead of `Makefile`, but essentially it's the same philosophy
- [gradle](https://gradle.org/), [sbt](https://www.scala-sbt.org/), [mill](https://mill-build.org/) use DSLs written in real programming languages (or custom flavors of those..): Groovy/Kotlin/Scala

---

I often wondered if there is a middle ground?
Something more powerful than plain XML/YAML/JSON/TOML, but less than a full blown DSL..  

The XML/YAML/JSON/TOML approach isn't flexible/readable and you almost always end up inventing a new DSL inside of it, like GH Actions and others do.  
By flexibility I mean defining multiple modules, cross compiling, cross platform modules etc. This gets hairy pretty quickly in Scala land where I come from.

With DSLs there is additional complexity of learning a new language and it's quirks, especially when it's untyped/mutable like Groovy, or arcane like sbt's task macros wonderland..  
Mill kinda hits the sweetspot, but still there is an extra compile step.
You need to "compile the build" before you "compile/run/etc your own code".
This might seem like slight annoyance but it gets slower and more annoying as your project gets bigger.

--

An alternative way could be to use one of the "configuration languages".  
You might have worked with HCL to write some Terraform, or Cue/Jsonnet/Pkl.  
These days I really like the look & feel of [Pkl](https://pkl-lang.org/).  
It has a nice IDE support, docs support, codegen etc.

The tasks graph still has to be constructed, but it will be done in the build tool itself, not exposed to the end user (this is what Maven does).
We gain more power to express multi module/platform/flavor of our code.
E.g. to make a cross jvm/js/native - scala 2/3 project, that would be 6 different modules, and it would be really ugly in XML/JSON/YAML.
We avoid having to maintain a custom flavor of Groovy/Kotlin/Scala, compiling it, maintaining backwards compatibility etc.  
This puts us somewhere around 4-5 hour on the "Configuration Complexity Clock", see the linked post!

With the tasks graph in place, not only we can execute dependent tasks as needed, but we also gain power to:
- execute only tasks whose sources have changed
- cache execution results to avoid unnecessary work
- implement the "watch mode" effectively


## Mini Build Tool

To start, let's define a `Task[T]`, a task that when runs returns a `T`.
```scala
class Task[T](
   val id: String,            // unique globally, e.g. mymodule.mytask
   val name: String,          // nice display name for CLI etc
   val taskDeps: Seq[String], // dependent task ids
   val execute: Seq[Any] => T,// the run logic
   val transitive: Boolean    // triggers downstream module(s) task with same id
) {
    def moduleId: String = id.split("\\.")(0)
}
```

The next step would be to make some task *implementations*:

```scala
class JavaModuleTasks(module: JavaModule) {

  val compile: Task[Seq[String]] = Task(
    id = s"${module.id}.compile",
    name = "compile",
    taskDeps = Seq.empty,
    depResults => {
      println(s"[module ${module.id}] Compiling java... " + Instant.now())
      Thread.sleep(1000)
      Seq("file1.class", "file2.class")
    },
    transitive = true // we want to compile all depending modules!
  )

  val run: Task[Unit] = Task(
    id = s"${module.id}.run",
    name = "run",
    taskDeps = Seq(s"${module.id}.compile"),
    depResults => {
      val classfiles = depResults.head.asInstanceOf[Seq[String]]
      println(s"[module ${module.id}] Running java -cp ${classfiles.mkString(" ")}")
    }
  )

  val all: Seq[Task[?]] = Seq(
    compile,
    run
  )
}
```

and a "registry" that knows which modules have which tasks:

```scala
object ModuleTasksRegistry {
  def getByModule(module: Module): Seq[Task[?]] = module match {
    case m: JavaModule => JavaModuleTasks(m).all
    case _             => Seq.empty
  }
}
```


---

Now we can start wiring it all together.
But we need a graph utility, so we will use [JGraphT](https://jgrapht.org/) for this.

First the modules graph:
```scala
import java.io.*
import java.net.*
import scala.jdk.CollectionConverters.*
import scala.jdk.FunctionConverters.*
import org.jgrapht.*
import org.jgrapht.graph.*
import org.jgrapht.traverse.*
import org.jgrapht.nio.*
import org.jgrapht.nio.dot.*
import org.jgrapht.alg.cycle.CycleDetector
import java.time.Instant

@main def serverMain() = {

  // parsed from build.yaml/json/whatever
  val a = JavaModule("a", Seq("b", "c"))
  val b = JavaModule("b", Seq("d"))
  val c = JavaModule("c", Seq("d"))
  val d = JavaModule("d", Seq.empty)
  val allModules = Seq(a, b, c, d)

  // these come from CLI/BSP
  val moduleId = "a"
  val taskName = "run"

  def buildModulesGraph(modules: Seq[Module]): SimpleDirectedGraph[Module, DefaultEdge] = {
    val graph = new SimpleDirectedGraph[Module, DefaultEdge](classOf[DefaultEdge])
    val modulesMap = modules.map(m => m.id -> m).toMap
    modules.foreach(graph.addVertex)
    modules.foreach { m =>
      m.moduleDeps.foreach { moduleDepId =>
        val moduleDep = modulesMap.getOrElse(
          moduleDepId,
          abort(s"Module referenced by '${m.id}' not found: '${moduleDepId}'")
        )
        graph.addEdge(m, moduleDep)
      }
    }
    graph
  }

  val modulesGraph = buildModulesGraph(allModules)
  checkNoCycles(modulesGraph, _.id)
  println("Modules graph:")
  println(generateDOT(modulesGraph, v => v.id, v => Map("label" -> v.id)))
```

You will see a nice image when you run it through Graphviz:

![](/images/mini_build_tool/modules.svg)


---

Then the tasks graph:
```scala
  val tasksPerModule = allModules.map { module => module.id -> ModuleTasksRegistry.getByModule(module) }.toMap
  
  def buildTasksGraph(modules: Seq[Module]): SimpleDirectedGraph[Task[?], DefaultEdge] = {
    val graph = new SimpleDirectedGraph[Task[?], DefaultEdge](classOf[DefaultEdge])
    for module <- modules do {
      val tasks = tasksPerModule(module.id)
      val tasksMap = tasks.map(t => t.id -> t).toMap
      tasks.foreach { task =>
        graph.addVertex(task)
        task.taskDeps.foreach { taskDepId =>
          val taskDep = tasksMap.getOrElse(
            taskDepId,
            abort(s"Task referenced by '${task.id}' not found: '${taskDepId}'")
          )
          graph.addEdge(task, taskDep)
        }
        // if this task triggers a task in depending module, e.g. compile->compile
        if task.transitive then
          module.moduleDeps.foreach { moduleDepId =>
            tasksPerModule(moduleDepId).find(_.name == task.name).foreach { moduleDepTask =>
              graph.addVertex(moduleDepTask) // add if not already
              graph.addEdge(task, moduleDepTask)
            }
          }
      }
    }
    graph
  }

  val tasksGraph = buildTasksGraph(allModules)
  checkNoCycles(tasksGraph, _.id)
  println("Tasks graph:")
  println(generateDOT(tasksGraph, v => v.id, v => Map("label" -> v.id)))
```

You will see a nice image when you run it through Graphviz:

![](/images/mini_build_tool/tasks.svg)


---

Now the execution subgraph (the exec plan). We get the task that we want to run, plus the tasks that it depends on:
```scala
  def buildExecSubgraph(moduleId: String, taskName: String): AsSubgraph[Task[?], DefaultEdge] = {
    val execTasksSet = Set.newBuilder[Task[?]]
    def go(moduleId: String, taskName: String): Unit = {
      val taskToExecute = tasksPerModule(moduleId).find(_.name == taskName).getOrElse {
        abort(s"Task not found ${moduleId}.${taskName}")
      }
      val deps = tasksGraph.outgoingEdgesOf(taskToExecute).asScala.toSeq
      deps.foreach { depEdge =>
        val d = tasksGraph.getEdgeTarget(depEdge)
        go(d.moduleId, d.name)
      }
      execTasksSet.addOne(taskToExecute)
    }
    go(moduleId, taskName)
    new AsSubgraph(tasksGraph, execTasksSet.result().asJava)
  }

  val execSubgraph = buildExecSubgraph(moduleId, taskName)
  checkNoCycles(execSubgraph, _.id)
  println("Exec subgraph:")
  println(generateDOT(execSubgraph, v => v.id, v => Map("label" -> v.id)))
```

You will see a nice image when you run it through Graphviz:

![](/images/mini_build_tool/plan.svg)

---

OMG there is more.. now we need the "execution stages".  
These are "independently runnable task stages", we can run each stage in parallel (the tasks in it that is..).  
This is just a dumb implementation, doesnt handle duplicates etc:
```scala
  def buildTaskExecStages(moduleId: String, taskName: String): Seq[Seq[Task[?]]] = {
    val taskToExecute = tasksPerModule(moduleId).find(_.name == taskName).getOrElse {
      abort(s"Task not found ${moduleId}.${taskName}")
    }
    var stages = Map.empty[Int, Seq[Task[?]]]
    var maxDepth = 0
    def go(task: Task[?], depth: Int): Unit = {
      if depth > maxDepth then maxDepth = depth
      stages = stages.updatedWith(depth) {
        case Some(values) => Some(if values.exists(_.id == task.id) then values else values.appended(task))
        case None         => Some(Seq(task))
      }
      val deps = tasksGraph.outgoingEdgesOf(task).asScala.toSeq
      deps.foreach { depEdge =>
        val d = tasksGraph.getEdgeTarget(depEdge)
        go(d, depth + 1)
      }
    }
    go(taskToExecute, 0)
    val res = for i <- 0 to maxDepth yield stages(i)
    res.toSeq
  }
  val taskExecStages = buildTaskExecStages(moduleId, taskName).reverse
  println("Exec stages:")
  println(taskExecStages.map(_.map(_.id)))
```

And now finally, finally we can run the tasks, in stages:
```scala
  def executeTasks(stages: Seq[Seq[Task[?]]]): Unit = {
    println("Starting execution... " + Instant.now())
    var taskResults = Map.empty[String, Any]
    for tasks <- stages do {
      val taskExecutions = for task <- tasks yield { () =>
        val args = task.taskDeps.map(taskResults)
        val taskRes = task.execute(args)
        task.id -> taskRes
      }
      val results = ox.par(taskExecutions)
      taskResults ++= results
    }
    println("Execution finished successfully. " + Instant.now())
  }

  executeTasks(taskExecStages)

}
```


These are utilities I used, just for completeness:
```scala
def checkNoCycles[V, E](g: Graph[V, E], getName: V => String): Unit = {
  val cycleDetector = new CycleDetector[V, E](g)
  val cycles = cycleDetector.findCycles().asScala
  if cycles.nonEmpty then abort(s"Cycle detected: ${cycles.map(getName).mkString("->")}")
}

def generateDOT[V, E](
    g: Graph[V, E],
    vertexIdProvider: V => String,
    vertexAttributeProvider: V => Map[String, String]
): String = {
  val vertexIdProvider0 = (v: V) => vertexIdProvider(v).replaceAll("[-.]", "_")
  val exporter = new DOTExporter[V, E](vertexIdProvider0.asJava)
  exporter.setVertexAttributeProvider { v =>
    vertexAttributeProvider(v)
      .mapValues(DefaultAttribute.createAttribute)
      .toMap
      .asJava
  }
  val writer = new StringWriter()
  exporter.exportGraph(g, writer)
  writer.toString()
}

def abort(message: String) =
  throw new RuntimeException(message)
```

---

When you run it, you should see something like this:
```shell
Starting execution... 2025-12-31T16:07:58.469767047Z
Executing d.compile with args: List()
[module d] Compiling java... 2025-12-31T16:07:58.499953484Z
Executing b.compile with args: List()
[module b] Compiling java... 2025-12-31T16:07:59.509806784Z
Executing c.compile with args: List()
[module c] Compiling java... 2025-12-31T16:07:59.510141441Z
Executing a.compile with args: List()
[module a] Compiling java... 2025-12-31T16:08:00.511292372Z
Executing a.run with args: List(List(file1.class, file2.class))
[module a] Running java -cp file1.class file2.class
Execution finished successfully. 2025-12-31T16:08:01.520049179Z
```


## Conclusion

This just sets up a barebone build tool tasks deps logic.  
There is **a lot more** that could/should? be done:
- build definition
- caching, usually using hash functions
- nice CLI
- error handling, very important!
- locking to prevent corrupt state, probably *the hardest part*
- BSP integration for IDEs
- plugins..

Some of that you can find in my ongoing project called [Deder](https://github.com/sake92/deder).  
I am trying to keep it as simple as possible.
Currently at ~5K lines of code with following features:
- Pkl for build definition
- multimodule
- client-server mode without blocking
- singleton server process for whole project, saving resources and simplifying locking logic
- watch mode, in multiple terminals at once!
- basic BSP support for IDEs like VSCode+Metals and IntelliJ

Contributions and feedback welcome!


### References
- Haoyi's [What does a Build Tool do?](https://mill-build.org/blog/8-what-is-a-build-tool.html) blog post
- Mike Hadlow's [Configuration Complexity Clock](https://mikehadlow.blogspot.com/2012/05/configuration-complexity-clock.html) blog post


