package site.en

import scalatags.Text.all._
import utils._
import utils.Imports._, grid._, Image._

object Index extends EnglishStaticPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Home Page")
      .withDescription(
        "blog.sake.ba is a blog by Sakib Hadžiavdić. " +
          "Tutorials in programming, math etc."
      )

  override def pageContent = frag(
    div(cls := "well well-lg col-md-6 col-md-push-3 pages-toc")(
      s"""
      #### Welcome! :)

      This is where I write my blog posts in English.

      Klikni [ovdje](${site.Index.ref}) za bosansku verziju!

      ---
      """.md
    )
  )

}
