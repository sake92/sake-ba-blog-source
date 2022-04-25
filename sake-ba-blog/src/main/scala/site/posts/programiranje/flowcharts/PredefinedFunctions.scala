package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images

object PredefinedFunctions extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Ugrađene funkcije")
      .withLabel("Ugrađene funkcije")
      .withDescription(
        "Ugrađene, predefinisane funkcije. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      Vidjeli smo kako izračunati jednostavne matematičke izraze sa operatorima +, -, *, / i %.  
      Za Boolean izraze imamo operatore &&, || i !.  
      Za Stringove imamo operator +, koji ih nadovezuje, "štikla" ih.

      U "pravim" programima ne izmišljamo toplu vodu,
      iskorištavamo već postojeći kod.  
      Taj kod može biti "ugrađen" u sami jezik, ili kod koji smo mi napisali.

      Moramo negdje "spremiti" taj kod, parametrizirati ga ako je potrebno.  
      Za to nam služe funkcije!

      ---
      Hajde da vidimo neke od ugrađenih/predefinisanih funkcija.  
      Probajte ispisati sljedeće izraze:
      - abs(-456.31)
      - real2int(3.14)
      - length("abcd")
      - randomInt(10)

      Sintaksa je ista kao i u matematici, napišemo ime funkcije, 
      a u zagradama navedemo argumente koji su razdvojeni zarezom.

      Gornji izrazi se nazivaju "pozivanje funkcije" (engl. "invoke").

      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
      ),
      br
    )
  )

}
