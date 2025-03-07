package files.posts.programiranje.java.oop

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*

object Index extends OopTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Uvod u OOP")
      .withLabel("OOP")
      .withDescription("Uvod u Objektno orijentisano programiranje, OOP, java.")

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2019, 12, 14))
      .withSections(
        paradigmeSection,
        oopSection
      )

  def paradigmeSection = Section(
    "Paradigme",
    """
        Jedan od značajnijih problema koje p.j. pokušavaju riješiti je **kompleksnost**.  
        Kako programi postaju veći, to su komplikovaniji za razumjeti.  
        Zato danas imamo nekoliko paradigmi (pristupa, načina) za razvijanje programa, od kojih su najpoznatije:
    
        - **proceduralno**
        - **funkcionalno** - FP
        - **objektno orijentisano** - OOP

        Dosta jezika danas je miks dvije ili više ovih paradigmi.  
        Ove paradigme *usmjeravaju naš način razmišljanja* o problemima po svom nekom šablonu/fazonu.

        Npr. kod proceduralnog/imperativnog načina razmišljanja **mijenjamo varijable** "u mjestu".  
        Ovdje moramo pamtiti/zamisliti koja je vrijednost neke varijable *u datom trenutku*, što je većini ljudi izazovan zadatak.

        Kod FP/matematičkog načina razmišljanja pravimo **nove varijable** a stare ne diramo! 
        To nam pomaže kod razumijevanja određenog dijela koda, ne moramo pamtiti u glavi stanje "cijelog svijeta" 
          i na šta utiče promjena date varijable.
        
        OOP objedinjuje podatke sa funkcijama(metodama) koje rade sa tim podacima.  
        Taj objedinjeni koncept naziva se objekat.  
        OOP nam pomaže i da *sakrijemo neke podatke i funkcije* za koje korisnici(drugi dijelovi koda) ne moraju znati.  
        Ovdje veliku ulogu ima kompajler koji nas ograničava i usmjerava nas u tim slučajevima.
        """.md
  )

  def oopSection = Section(
    "OOP",
    s"""
    Ovo što smo do sada radili u [Javi](${files.posts.programiranje.java.Index.ref}) je proceduralno programiranje.  
    Koristili smo procedure/funkcije i mijenjali varijable u mjestu.  
    Klase smo koristili da kreiramo objekte koji sadrže *samo podatke*.

    U OOP klase ćemo koristiti na malo fleksibilniji način.
    """.md
  )

}
