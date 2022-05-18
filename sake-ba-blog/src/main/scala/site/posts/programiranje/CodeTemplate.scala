package site.posts.programiranje

import ba.sake.hepek.prismjs
import ba.sake.hepek.prismjs.{PrismDependencies, Themes}
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
      .withVersion("1.27.0")
      .withTheme(Themes.Twilight)
      .withLanguages(prismjs.PrismConsts.languages.filter(hlLangs.contains))

}
