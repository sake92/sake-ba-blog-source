package files.posts.programiranje

import ba.sake.hepek.prismjs
import ba.sake.hepek.prismjs.{PrismDependencies, PrismThemes}
import templates.SakeBaBlogPage

trait CodeTemplate extends SakeBaBlogPage with PrismDependencies {

  private val hlLangs = List(
    "core",
    "basic",
    "c",
    "clike",
    "batch",
    "scala",
    "java",
    "javascript",
    "haskell",
    "markup",
    "markup-templating",
    "vbnet"
  )

  override def prismSettings =
    super.prismSettings
      .withTheme(PrismThemes.Twilight)

}
