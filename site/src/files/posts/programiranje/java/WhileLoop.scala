package files.posts.programiranje.java

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._

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
      """.md
    )
  )
}
