import com.typesafe.sbt.web.Import.WebKeys

inThisBuild(
  List(
    scalaVersion      := "3.1.0",
    scalafmtOnCompile := true,
    resolvers +=
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  )
)

lazy val sakeBaBlog = (project in file("sake-ba-blog"))
  .settings(
    libraryDependencies ++= Seq(
      "ba.sake" %% "hepek" % "0.9.0+21-f3505781+20220424-0428-SNAPSHOT"
    ),
    (hepek in Compile) := {
      WebKeys.assets.value
      (hepek in Compile).value
    },
    WebKeys.webModulesLib := "site/lib",
    pdfGenerate           := pdfGenerateTask.value
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
