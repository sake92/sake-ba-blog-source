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
          "Tutorijali iz programiranja, java, scala, matematika i slično."
      )

  override def pageContent = frag(
    div(cls := "col-md-6 col-md-push-3 pages-toc")(
      row(
        s"""
        # Dobrodošli na moje tutorijale!

        Moje ime je Sakib i drago mi je da ste ovdje. :)  
        Ovdje ćete pronaći tutorijale za programiranje i još ponešto.  

        > For English version [click here](${site.en.Index.ref}).

        ---

        Ako ste totalni početnik, i želite naučiti programirati,
        preporučujem da počnete sa [dijagramima toka](${posts.programiranje.flowcharts.Index.ref}).
        Dosta je lakše početi s *vizuelnim programiranjem*, jer praćenjem linija lakše je shvatiti kako program radi.  
        Ovi tutorijali vode vas kroz osnovne gradivne dijelove programa.

        ---
        Nakon toga možete nastaviti s [Java](${posts.programiranje.java.Index.ref}) programskim jezikom.  
        Tu ćete se upoznati sa jednim vrlo popularnim, traženim i plaćenim programskim jezikom.  
        Vrlo je sličan drugim popularnim jezicima kao što su C#, JavaScript i ostali.  
        Tako da ako se odlučite i za neki drugi jezik kasnije, neće vam biti teško da ga savladate.

        ---
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
            Tutorijali u PDF formatu (nisu uvijek najnovije verzije):
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
