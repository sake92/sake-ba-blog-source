package files

import scalatags.Text.all._
import utils._
import utils.Imports.*

object Index extends templates.SakeBaBlogStaticPage {
  
  override def pageSettings =
    super.pageSettings
      .withTitle("Početna stranica")
      .withDescription(
        "blog.sake.ba je blog fizičke osobe pod imenom Sakib Hadžiavdić. " +
          "Tutorijali iz programiranja, java, scala, matematika i slično."
      )

  override def pageContent = tag("main")(cls:="pico container")(
        s"""
        # Saketovi tutorijali

        Moje ime je Sakib, bujrum! :)  
        Ovdje ćete pronaći tutorijale za programiranje i još ponešto.  

        > If you are looking for my blog in English [click here](${en.Index.ref}).

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
      ,
      "Mapa sajta:".md,
      div(SiteMapHTML.siteMap(Site.bs.mainPages)),
      s"""
            Tutorijali u PDF formatu (nisu uvijek najnovije verzije):
            - [Uvod u programiranje](${Site.bs.programiranjePdf.ref})
            - [Scala](${Site.bs.scalaPdf.ref})
            - [Matematika](${Site.bs.matematikaPdf.ref})
          """.md
    
  )

}
