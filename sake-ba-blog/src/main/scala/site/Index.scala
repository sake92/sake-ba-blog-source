package site

import scalatags.Text.all._
import utils._
import utils.Imports._, grid._, Image._

object Index extends templates.SakeBaBlogStaticPage {

  override def pageSettings =
    super.pageSettings
      .withTitle("Početna stranica")
      .withDescription(
        "blog.sake.ba je blog fizičke osobe pod imenom Sakib Hadžiavdić. " +
          "Tutorijali iz programiranja, matematike i slično."
      )

  override def pageContent = frag(
    div(cls := "well well-lg col-md-6 col-md-push-3 pages-toc")(
      row(
        half(
          "Mapa sajta:".md,
          div(SiteMapHTML.siteMap)
        ),
        half(
          s"""
            Tutorijali u PDF-u:
            - [Uvod u programiranje](${Site.programiranjePdf.ref})
            - [Scala](${Site.scalaPdf.ref})
            - [Matematika](${Site.matematikaPdf.ref})
          """.md
        )
      )
    )
  )

}
