package site.posts.programiranje.java

import java.time.LocalDate

import ba.sake.hepek.plantuml.PlantumlHelpers._
import scalatags.Text.all._
import utils.Imports._

object SwitchStatement extends JavaTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Switch naredba")
      .withDescription("Switch naredba u Javi.")

  override def blogSettings =
    super.blogSettings
    //.withCreatedDate(LocalDate.of(2018, 2, 4))
      .withSections(switchSection)

  def switchSection = Section(
    "Switch naredba",
    div(
      """
        Grananje pomoću `switch-case` naredbe može učiniti kod dosta preglednijim.  
        Ova naredba se koristi umjesto mnoštva if-else grana, 
          od koje svaka grana provjerava **je li varijabla jednaka** nekoj vrijednosti.  
        U Javi možete "switchati" cijele brojeve, karaktere, stringove i enumeracije.
      """.md,
      chl.java.withLineHighlight("2,6-8")(
        """
        int i = 5;
        switch (i) {
            case 3:
                System.out.println("Tri");
                break;
            case 5:
                System.out.println("Pet");
                break;
            default:
                System.out.println("Ne znam...");
                break;
        }
      """
      ),
      """
          Na liniji 2 ispituje se čemu je **jednaka vrijednost varijable `i`**, 
            **redom odozgo** naravno.  
          Prvi slučaj (en. case) koji bude ispunjen će biti izvršen.  
          Ako je vrijednost varijable `i` broj 5, ispisaće se na ekran "Pet".
          
          Često ne znamo koliko ima mogućih slučajeva, 
            pa onda trebamo odlučiti šta da uradimo po tom pitanju.  
          Za to nam služi ključna riječ `default`. To je ustvari ona `else` grana u `if` naredbi! ;)
      """.md
    )
  )

}
