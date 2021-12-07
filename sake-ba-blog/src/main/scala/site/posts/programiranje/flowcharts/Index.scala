package site.posts.programiranje.flowcharts

import java.time.LocalDate
import scalatags.Text.all._
import utils.Imports._, Image._
import images.Images

object Index extends FlowchartsTemplate {

  val zipFile = resources.resource("files/flowcharts/flowgorithm.zip").ref

  override def pageSettings =
    super.pageSettings
      .withTitle("Uvod u dijagrame toka")
      .withLabel("Početna")
      .withDescription("Uvod u dijagrame toka.")

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2020, 12, 16))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    s"""
    Kompjuteri izvršavaju **naredbe** jednu za drugom.  
    Naredbe su logički grupisane u **programe**.  
    Jedan program sadrži naredbe koje kompjuter treba izvršiti.
    
    Program se može predstaviti **vizuelno** pomoću **dijagrama toka** (engl. flowchart).  
    Vrlo praktična aplikacija koju ćemo koristiti za to je [Flowgorithm](http://www.flowgorithm.org/download/index.htm).  
    Molim vas da skinete Flowgorithm i instalirate!
    
    Svi primjeri mogu se downloadati u ZIP formatu [ovdje]($zipFile).
    """.md,
    List(firstProgSection, helloWorldSection, mathSection, textSection)
  )

  def firstProgSection =
    Section(
      "Prvi program u Flowgorithmu",
      frag(
        s"""
        Svaki program ima svoj **početak** i **kraj**.  
        U Flowgorithmu početak je označen sa `Main`, kraj je označen sa `End`.  
        Strelica nakon svake naredbe pokazuje na **iduću naredbu** koja se treba izvršiti.
        
        Ako kliknemo na dugme `Run` (trokutić koji izgleda kao "play" dugme),
        program će se **pokrenuti i odmah završiti**!  
        Nismo vidjeli da se išta desilo... :D  
        Samo vidimo u lijevom donjem uglu "Program has finished running."
        """.md,
        image(Images.flowcharts.intro.program.ref, 1093, 657)
      )
    )

  def helloWorldSection =
    Section(
      "Pozdrav svijete!",
      frag(
        s"""
        Hajde da ispišemo nešto na ekran:
        - kliknite na strelicu, prikazaće se lista naredbi koje možemo dodati na to mjesto.  
        - kliknite na `Output`
        - dvokliknite na novu Output naredbu
        - upišite `"Pozdrav svijete!"` (sa navodnicima!) i klikni OK
        
        Ako sada kliknemo Run, vidjećemo novi prozor sa porukom `"Pozdrav svijete!"`.  
        """.md,
        image(
          Images.flowcharts.intro.zdravo_svijete.ref,
          1920,
          1020,
          "Pozdrav svijete!"
        ),
        """
        
        Čestitam, napisali smo svoj prvi program!
        
        ##### Vježbe
        1. Ispišite `123` na ekran
        1. Ispišite `12 + 3` na ekran
        1. Ispišite `3.14` na ekran  
        (brojevi sa zarezom se ustvari pišu sa tačkom!)
        """.md
      )
    )

  def mathSection =
    Section(
      "Matematičke operacije",
      """
      Naravno, možemo raditi matematičke operacije nad brojevima.
      Probajte ispisati sljedeće izraze:
      - `10 + 3`
      - `10 - 3`
      - `10 * 3`
      - `10 / 3`
      - `10 % 3`
      
      Zadnja operacija je malkice misteriozna.  
      Ona daje **cjelobrojni ostatak pri dijeljenju**.  
      Npr. `10 / 3` je `3` i ostatak `1`. :)  
      Tako da je `10 % 3 = 1`.
      
      Naravno, možemo koristiti zagrade ako nam trebaju:
      - `(2 + 2) * 2`
      - `2 + (2 * 2)`
      """.md
    )

  def textSection =
    Section(
      "Spajanje teksta!",
      """
      U prethodnim primjerima ispisivali smo neke jednostavne vrijednosti.  
      Nekad nam treba da te vrijednosti "spojimo" u jednu.  
      Za to nam služi znak `&`:
      - `"abc" & "xyz"` bi ispisalo isto kao i `"abcxyz"`
      - `"abc" & 5` je isto kao `"abc5"`
      - `7 & 5` je isto kao .. ? Šta mislite? :D  
      Probajte sami!
      - `7 & 5 + 1`
      
      Probajte razne kombinacije, brojeve sa zarezom itd.  
      Probajte spojiti više od 2 vrijednosti!
      
      Malo terminologije:
      > - tekst koji se piše sa navodnim znacima, npr. `"Meho"` naziva se **String**
      > - broj bez zareza naziva se **Integer** (engl. "cijeli broj")
      > - broj sa zarezom/tačkom naziva se **Real** (engl. "realni broj")
      """.md
    )

}
