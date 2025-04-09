package files.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*

object ReturnFunctions extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Definisanje funkcije 2")
      .withLabel("Definisanje funkcije 2")
      .withDescription(
        "Definisanje funkcije s povratnom vrijednosti. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      // .withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      U prethodnom dijelu vidjeli smo kako možemo grupisati dio koda u funkciju.  
      Funkciju možemo parametrizovati sa argumentima kada ju pozivamo.  
      Funkcije također mogu da *vrate vrijednost*.  

      ---
      Hajde da napravimo funkciju `kvadrat(x)`.  
      Izračunat će kvadrat broja x i vratiti ga kao vrijednost:
      - dodajte novu funkciju `kvadrat` sa tipom Integer
      - dodajte novi parametar `Integer x`
      - kliknite na `return` element i upišite `x*x`
      - u main funkciji ispišite `kvadrat(5)`

      Program će ispisati 25 kada ga pokrenete.

      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-f-d-o")()
      ),
      br
    )
  )

}
