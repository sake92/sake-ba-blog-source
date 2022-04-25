package site.posts.programiranje.java

import java.time.LocalDate
import scalatags.Text.all._
import ba.sake.hepek.plantuml.PlantumlHelpers._
import utils.Imports._

// svg editor: https://www.planttext.com/
object ForLoop extends JavaTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("For petlja")
      .withDescription("For petlja u Javi.")

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2018, 2, 4))
      .withSections(forSection)

  def forSection = Section(
    "For naredba",
    div(
      """
        Java ima i tzv. `for` petlje. To su haman-ha `while` petlje s finijom sintaksom.  
        Sljedeći primjer radi isto kao i prethodni sa `while` petljom.
      """.md,
      chl.java.withLineHighlight("1")(
        """
          for (int i = 0; i < 10; i = i + 1) {
              System.out.println("Cifra i je: " + i);
          }
        """
      ),
      """
          Uopćena sintaksa for petlje je sljedeća: `for(inicijalizacije; uslov; koraci)`.  
          Dijelovi `for` petlje su razdvojeni tačkazarezom.  

          Umjesto da deklarišemo varijable prije petlje, zgodno je deklarisati ih u sklopu petlje, 
            jer nam poslije petlje one ne trebaju.  
          Za to nam služi dio **inicijalizacije**.  
          
          Dio **uslov** je isti kao i kod while petlje.  
          
          Dio **koraci** dolazi **na kraju petlje**, isto kao linija 4 u našoj `while` petlji.  
          Koraci obično *utiču na uslov za izlaz* iz petlje.  
      """.md
    )
  )

}
