package site.posts.programiranje.flowcharts

import java.time.LocalDate
import scalatags.Text.all._
import utils.Imports._, Image._
import images.Images

object Index extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Vizuelno programiranje")
      .withLabel("Uvod")
      .withDescription(
        "Uvod u vizuelno programiranje, dijagrami toka, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 04, 24))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    s"""
    Kompjuteri izvršavaju **naredbe** jednu po jednu, redom.  
    Naredbe su logički grupisane u **programe**.  
    Program sadrži naredbe koje kompjuter treba izvršiti.
    
    Program se može predstaviti **vizuelno** pomoću **dijagrama toka** (engl. flowchart).  
    Koristićemo ugrađeni editor, koji može i pokrenuti program koji napravimo.  
    
    ---
    Ako želite da sačuvate svoje programe koje napravite, možete se registrovati na [flowrun.io](https://flowrun.io/).  
    Tu možete vidjeti i neke programe koje su napravili drugi korisnici, kliknite [ovdje](https://flowrun.io/explore).
    """.md,
    List(firstProgSection, exercisesSection)
  )

  def firstProgSection =
    Section(
      "Pozdrav svijete!",
      frag(
        s"""
        Svaki program ima svoj **početak** i **kraj**.  
        Početak je označen sa `Begin`, kraj je označen sa `End`.  
        Strelica pokazuje na **iduću naredbu** koja se treba izvršiti.
        
        Ako kliknemo na dugme `Run` (▶),
        program će se **pokrenuti i odmah završiti**!  
        Nije se ništa značajno desilo, samo vidimo u output panelu da je program završio.
        """.md,
        div(
          div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
        ),
        br,
        s"""
        Hajde da ispišemo nešto na ekran:
        1. kliknite na strelicu, prikazaće se lista naredbi koje možemo dodati
        1. kliknite na `Output`
        1. dvokliknite na novu Output naredbu
        1. upišite `"Pozdrav svijete!"` (sa navodnicima!) i klikni OK
        1. kliknite Run
        
        Vidjećemo da program sada ispiše poruku `"Pozdrav svijete!"`.  
        """.md
      )
    )

  def exercisesSection =
    Section(
      "Vježbe",
      frag(
        """
        Čestitam, napisali smo svoj prvi program!  
        Probajte odraditi sljedeće vježbe:
        
        #### Ispis
        1. Ispišite `123` na ekran
        1. Ispišite `12 + 3` na ekran
        1. Ispišite `3.14` na ekran  
        (brojevi sa zarezom se ustvari pišu sa tačkom!)

        #### Matematičke operacije
        Naravno, možemo raditi matematičke operacije nad brojevima.  
        Ispišite sljedeće izraze:
        - `10 + 3`
        - `10 - 3`
        - `10 * 3`
        - `10 / 3`
        - `10 % 3`
        
        Zadnja operacija je malkice misteriozna.  
        Ona daje **cjelobrojni ostatak pri dijeljenju**.  
        Npr. `10 / 3` je `3` i ostatak `1`.  
        Tako da je `10 % 3 = 1`.
        
        Naravno, možemo koristiti zagrade ako nam trebaju:
        - `(2 + 2) * 2`
        - `2 + (2 * 2)`

        #### Spajanje teksta

        U prethodnim primjerima ispisivali smo neke jednostavne vrijednosti.  
        Nekad nam treba da te vrijednosti spojimo u jednu.  
        Za to nam služi znak `+`:
        - `"abc" + "xyz"` bi ispisalo isto kao i `"abcxyz"`
        - `"abc" + 5` je isto kao `"abc5"`
        
        Probajte razne kombinacije, brojeve sa zarezom itd.  
        Probajte spojiti više od 2 vrijednosti!
        
        Malo terminologije:
        > - tekst koji se piše sa navodnim znacima, npr. `"Meho"` naziva se **String**
        > - broj bez zareza naziva se **Integer** (engl. "cijeli broj")
        > - broj sa zarezom/tačkom naziva se **Real** (engl. "realni broj")
      """.md
      )
    )

}
