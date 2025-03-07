package files.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*
import ba.sake.hepek.html.statik.Section

object CustomFunctions extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Definisanje funkcije")
      .withLabel("Definisanje funkcije")
      .withDescription(
        "Definisanje funkcije. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      Predefinisane funkcije su super.  
      Ali još bolje je kada znamo napisati svoju funkciju!

      Prije nego nastavimo, moram spomenuti da već imamo jednu funkciju koja se zove "main".  
      Ova funkcija je vrlo važna, jer je ona funkcija od koje **počinje izvršavanje programa** kada pritisnete Play.

      ---
      Hajde da definišemo funkciju `pozdrav(ime)`:
      - dodajte novu funkciju u lijevom panelu
      - kliknite na `fun1` na dijagramu i promijenite ime u `pozdrav`
      - kliknite + da dodate novi parametar `String ime`
      - ispišite `"Pozdrav " + ime`
      - kliknite na `main` funkciju
      - dodajte novu Call naredbu i upišite `pozdrav("Meho")`

      Program bi trebao ispisati `Pozdrav Meho`.

      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-f-d-o")()
      ),
      br
    )
  )

}
