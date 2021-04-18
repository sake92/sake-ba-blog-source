package site.posts.programiranje.java

import java.time.LocalDate

import ba.sake.hepek.plantuml.PlantumlHelpers._
import scalatags.Text.all._
import utils.Imports._

// svg editor: https://www.planttext.com/
object WhileLoop extends JavaTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("While petlja")
      .withDescription("While petlja u Javi.")

  override def blogSettings =
    super.blogSettings
    //.withCreatedDate(LocalDate.of(2018, 2, 4))
      .withSections(loopsSection)

  def loopsSection = Section(
    "Ponavljanje naredbi",
    div(
      """
        Kada želimo ponavljati izvršiti neki dio koda više puta,
        najjednostavniji način je copy-paste. 
        Kopiramo ga n-puta i završeno! :D

        Međutim, ovo nije fleksibilno kada kasnije želimo da izmijenimo taj djelić koda.  
        Tada moramo izmijeniti sve linije... :/
        
        Zato postoje specijalne naredbe za **ponavljanje**: `while` i `for`.  
        Zovu se još i "petlje", jer se vrlo lahko zapetljati u njima.  
      """.md
    ),
    List(whileSection)
  )

  def whileSection = Section(
    "While naredba",
    div(
      """         
        U sljedećem primjeru želimo izvršavati kod sve dok je neki **uslov** ispunjen (en. while je "dok").  
       """.md,
      chl.java.withLineHighlight("2")(
        """
          int i = 0;
          while(i < 10) {
              System.out.println("Cifra i je: " + i);
              i = i + 1;
          }
          
        """
      ),
      """
        Naravno, unutar tog bloka **uslov se mora mijenjati**, inače se program *neće nikad zaustaviti*.  
        To se naziva **beskonačna petlja** i obično nije poželjna... :)

        Uslov se ispituje u svakoj **iteraciji** petlje. Dakle, svaki put kada se dođe do linije 5 uslov će se ponovo ispitati.
        Ako uslov nije zadovoljen, petlja se zaustavlja i program se nastavlja izvršavati na liniji 6.
        Slijedi i vizuelni dijagram izvršenja ove naredbe:
      """.md,
      div(Classes.txtAlignCenter)(
        plantSvg("""
          @startuml
          skinparam backgroundColor #EEEBDC
          skinparam shadowing false

          start

          partition While {
            while (**uslov?**) is (jeste)
              :naredbe while petlje;
              -[#blue,bold]-> loop;
            endwhile (nije)
          }

          :ostale naredbe;

          @enduml
        """)
      )
    )
  )
}
