package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images

object DoWhile extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Do While petlja")
      .withLabel("Do While")
      .withDescription(
        "Do While petlja. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      Ova naredba je slična While.  
      Glavna razlika je da DoWhile provjerava uslov **tek nakon što izvrši naredbe**.  
      DoWhile će izvršiti blok naredbi **minimalno jednom**.

      ---
      Napravićemo program koji čita broj od korisnika, koji mora biti pozitivan:
      - deklarišite Integer x
      - dodajte DoWhile sa uslovom `x <= 0`
      - unesite `x` u tijelu DoWhile

      Ovaj program možemo pročitati ovako:  
        "Unesi broj x, dok god je manji ili jednak nuli"

      Kada pokrenete program probajte unijeti sljedeće vrijednosti: 1, 17, -55, 6.  
      DoWhile će završiti samo kada unesete pozitivan broj.


      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
      ),
      br,
      """
      ##### Vježbe
      1. Unosite String s i ispišite ga, sve dok ne bude jednak "END"
      1. Unosite jednu po jednu ocjenu dok korisnik ne unese `0`. Zatim ispišite prosjek tih ocjena
      """.md
    )
  )

}
