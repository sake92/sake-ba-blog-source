package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images

object ForLoop extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("For petlja")
      .withLabel("For")
      .withDescription(
        "For petlja. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      Ova naredba je malo komplikovanija.  
      For petlja sadrži sljedeće dijelove:
      - naziv varijable
      - početna vrijednost - start
      - krajnja vrijednost - end
      - inkrement/korak - step
      - tijelo/narebe
      
      Izvršavanje For petlje je isto kao sljedeće:
      - Deklarisanje `Integer i` sa početnom vrijednošću
      - While petlja sa uslovom `i <= end` i tijelom
      - Assign `i = i + step` na kraju While

      ---
      Napravićemo program isti kao za While petlju, printanje prvih 5 brojeva:
      - deklarišite For petlju sa varijablom `i`
      - početna vrijednost je 1
      - krajna vrijednost je 5
      - korak je 1
      - ispišite `i` u tijelu For petlje
      
      Postigli smo isti rezultat kao sa While, samo sa dosta manje koda!  
      Plus, ovako je dosta čitljivije.

      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
      ),
      br,
      """
      ##### Vježbe
      1. Ispišite svako 5-i broj od 0..50
      1. Ispišite tablicu množenja od 1 do 10
      1. Izračunajte faktorijel broja 7
      """.md
    )
  )

}
