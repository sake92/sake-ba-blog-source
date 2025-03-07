package files.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*

object Input extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Unos podataka")
      .withLabel("Unos podataka")
      .withDescription(
        "Unos podataka. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 24))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    """
    U prethodnim primjerima koristili smo vrijednosti koje su ufiksane u program.  
    Npr. vrijednost `5` za dužinu stranice kvadrata.  
    Bilo bi zgodno kada bi mogli **dati korisniku da unese proizvoljnu vrijednost**.  
    Naravno, to je moguće, i za to služi naredba `Input`.
    """.md,
    List(unosSection)
  )

  def unosSection =
    Section(
      "Unos podataka",
      frag(
        s"""
        U donjem prozoru uradite sljedeće:
        - deklarišite varijablu `String ime`
        - nakon nje dodajte `Input` naredbu
        - kliknite na novu `Input` naredbu i unesite `ime`.  
          Ovo znači da kada program dođe do ove linije, pitaće korisnika da unese svoje `ime`.  
          Vrijednost koju korisnik upiše program će spremiti u varijablu `ime`
        - ispišite `ime`
        """.md,
        div(
          div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
        ),
        br,
        """
        Program sada čeka da unesemo neki tekst.  
        Unesite svoje ime i kliknite Enter.
        
        Vidimo da program odgovara ispisom u izlaznom panelu.

        ##### Vježbe
        1. Izmijeniti primjer sa kvadratom, omogućiti korisniku da unese dužinu stranice.
        1. Napraviti program za unos 2 stranice pravougaonika, zatim ispisati njegov obim.
        
        ---
        """.md
      )
    )
}
