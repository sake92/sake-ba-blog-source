package files.en

import scalatags.Text.all._
import utils._
import utils.Imports.*
import templates.EnStaticPage

object Index extends EnStaticPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Home Page")
      .withDescription(
        "blog.sake.ba is a blog by Sakib Hadžiavdić. " +
          "Tutorials in programming, math etc."
      )

  override def pageContent = tag("main")(cls:="pico container")(
    div(
      s"""
      #### Welcome! :)

      > [Ovdje](${files.Index.ref}) je bosanska verzija.
      
      Site map:
      
      """.md,
      div(SiteMapHTML.siteMap(Site.en.mainPages))
    )
  )

}
