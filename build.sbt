import com.typesafe.sbt.web.Import.WebKeys

inThisBuild(
  List(
    scalaVersion := "2.13.4",
    scalafmtOnCompile := true
  )
)

lazy val sakeBaBlog = (project in file("sake-ba-blog"))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake"                %% "hepek"                    % "0.8.9",
      "org.scala-lang.modules" %% "scala-collection-contrib" % "0.2.1"
    ),
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "site/lib",
    pdfGenerate := pdfGenerateTask.value
  )
  .enablePlugins(HepekPlugin, SbtWeb)

// custom handy tasks
val pdfGenerate = taskKey[Unit]("Generate PDFs")

val pdfGenerateTask = Def.taskDyn {
  (hepek in Compile).value
  val hepekTargetFolder = hepekTarget.value // must evaluate here...
  Def.task {
    (runMain in Compile).toTask(" hepek.PdfGenApp " + hepekTargetFolder).value
  }
}
