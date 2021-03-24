package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images

object Input extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Unos podataka")
      .withLabel("Unos podataka")
      .withDescription("Unos podataka")

  override def blogSettings =
    super.blogSettings
      .withCreatedDate(LocalDate.of(2020, 12, 16))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    """
    U prethodnim primjerima koristili smo vrijednosti koje su ufiksane u program.  
    Npr. vrijednost `a = 5` za dužinu stranice kvadrata.  
    Bilo bi zgodno kada bi mogli **dati korisniku da unese proizvoljnu vrijednost**.  
    Naravno, to je moguće, i u Flowgorithmu imamo naredbu `Input`.
    """.md,
    List(unosSection)
  )

  def unosSection =
    Section(
      "Unos podataka",
      frag(
        s"""
        - otvorite novi Flowgorithm prozor
        - deklarišite varijablu `ime`, sa tipom `String`
        - kliknite na strelicu i odaberite `Input` naredbu
        - dvokliknite tu novu naredbu i unesite `ime`.  
          Ovo znači da kada program dođe do ove linije, pitaće korisnika da unese svoje ime.  
          Mi ćemo tu vrijednost spremiti u varijablu `ime`
        - ispišite ime
        """.md,
        image(Images.flowcharts.input.input_1.ref, "Program čeka na unos"),
        """
        Vidimo da program sada čeka da unesemo neki tekst.  
        Da bi nam pomogao, Flowgorithm je ispisao "Please enter a value of type String for ime".  
        Unesite svoje ime i kliknite Enter.
        
        Vidimo da program odgovara ispisom na konzolu:
        """.md,
        image(Images.flowcharts.input.input_2.ref, "Ispis unesene vrijednosti"),
        """
        ##### Vježbe
        1. Izmijeniti primjer sa kvadratom, omogućiti korisniku da unese dužinu stranice.
        1. Napraviti program za unos 2 stranice pravougaonika, zatim ispisati njegov obim.
        
        ---
        """.md
      )
    )
}
