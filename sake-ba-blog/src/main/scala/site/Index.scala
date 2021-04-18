package site

import scalatags.Text.all._
import utils._
import utils.Imports._, grid._, Image._

object Index extends templates.SakeBaBlogStaticPage {

  val well = div(cls := "well well-lg")

  override def pageSettings =
    super.pageSettings
      .withTitle("Početna stranica")
      .withDescription(
        "blog.sake.ba je blog fizičke osobe pod imenom Sakib Hadžiavdić. " +
          "Tutorijali iz programiranja, matematike i slično."
      )

  override def pageContent = frag(
    div(cls := "col-md-6 col-md-push-3 pages-toc")(
      well(
        s"""
        #### Pozdrav i dobrodošli! :)

        Ako ste totalni početnik, i želite naučiti programirati,
        preporučujem da počnete sa [dijagramima toka](${posts.programiranje.flowcharts.Index.ref}).  
        Dosta je lakše početi sa *vizuelnim programiranjem*, a i kasnije će vam biti lakše razumjeti
        tekstualnu reprezentaciju programa.

        > [Here](${site.en.Index.ref}) is the English version of this blog.
        """.md
      ),
      row(
        half(
          well(
            "Mapa sajta:".md,
            div(SiteMapHTML.siteMap(Site.bs.mainPages))
          )
        ),
        half(
          well(
            s"""
            Tutorijali u PDF-u:
            - [Uvod u programiranje](${Site.bs.programiranjePdf.ref})
            - [Scala](${Site.bs.scalaPdf.ref})
            - [Matematika](${Site.bs.matematikaPdf.ref})
          """.md
          )
        )
      )
    )
  )

}
