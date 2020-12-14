package site.posts.programiranje.java.oop

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images
import site.posts.programiranje.CodeTemplate
import utils.Imports.grid._

object Index extends CodeTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Uvod u OOP")
      .withLabel("Početna")
      .withDescription("Uvod u OOP.")

  override def blogSettings =
    super.blogSettings
      .withCreatedDate(LocalDate.of(2019, 12, 14))
      .withSections(
        paradigmeSection
      )

  def paradigmeSection = Section(
    "Paradigme",
    """
        Jedan od značajnijih problema koje p.j. pokušavaju riješiti je **kompleksnost**.  
        Kako programi postaju veći, to su komplikovaniji za razumjeti.  
        Zato danas imamo nekoliko paradigmi (pristupa, načina) za razvijanje programa, od kojih su najpoznatije:
    
        - **proceduralno**
        - **funkcionalno**
        - **objektno orijentisano**
        - **logičko**

        Dosta jezika danas je miks dvije ili više ovih paradigmi.  
        Ove paradigme *usmjeravaju naš način razmišljanja* o problemima po svom nekom šablonu/fazonu.

        Npr. kod proceduralnog/imperativnog načina razmišljanja **mijenjamo varijable** "u mjestu".  
        Ovdje moramo pamtiti/zamisliti koja je vrijednost neke varijable *u datom trenutku*, što je većini ljudi izazovan zadatak.

        Kod funkcionalnog/matematičkog načina razmišljanja pravimo **nove varijable** a stare ne diramo! 
        To nam pomaže kod razumijevanja određenog dijela koda, ne moramo pamtiti u glavi stanje "cijelog svijeta" 
          i na šta utiče promjena date varijable. Ali, otom potom...
        """.md
  )

}
