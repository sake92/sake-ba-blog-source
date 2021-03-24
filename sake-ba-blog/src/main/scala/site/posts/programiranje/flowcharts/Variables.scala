package site.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports._
import Image._
import images.Images

object Variables extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Varijable")
      .withLabel("Varijable")
      .withDescription("Uvod u varijable.")

  override def blogSettings =
    super.blogSettings
      .withCreatedDate(LocalDate.of(2020, 12, 16))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    """
    Varijable nam služe za **spremanje informacija/podataka**.  
    Npr. ako računamo prosjeke ocjena učenika (29 ih ima), morali bi pisati broj 29 više puta u programu.  
    Ako dođe novi učenik, morali bi na svim mjestima u programu izmijeniti 29 na 30... :D  
    Varijable nam pomažu upravo za ovakve stvari!  
    
    ---
    Sve varijable imaju **naziv** i **tip**.  
    Tip nam ograničava **šta možemo spremiti u tu varijablu**:
    - `Integer` - cijeli broj
    - `Real` - realni broj
    - `String` - tekst
    - `Boolean` - jeste/nije (engl. `true`/`false`)
    
    ---
    Napravićemo program koji će izračunati površinu i obim kvadrata.  
    Ovdje će nam trebati varijabla `Integer a`, cijeli broj koji ćemo mi nazvati `a`. 
    To je ustvari *dužina stranice kvadrata* (sve 4 su iste..).  
    Naravno, mogli smo dati bilo koji drugi naziv: `xyz`, `fdgfdgfdxcv` itd.  
    Ali varijable moraju imati **smislene nazive**, da bi kasnije mogli lakše razumjeti program.  
    """.md,
    List(declarationSection, obimSection, povrsSection)
  )

  def declarationSection =
    Section(
      "Deklaracija i dodjela varijable",
      frag(
        s"""
        1. otvorite novi Flowgorithm prozor
        1. deklarišite varijablu `a`:
            - kliknite na strelicu
            - odaberite `Declare`
            - dvokliknite novi Declare blok i unesite naziv `a`.  
              Primijetite da u donjem lijevom uglu stoji tip varijable (Integer)!  
              Kasnije ga možemo promijeniti ako trebamo.
            - kliknite OK
        1. dodijelite vrijednost varijabli `a`:
            - kliknite na strelicu
            - odaberite `Assign`
            - dvokliknite novi Assign blok i unesite naziv `a` i vrijednost `5` (Expression, desni dio)
            - kliknite OK
        
        Ovdje ćemo malo zastati. Prvo probajte ispisati varijablu `a` na ekran.  
        To smo naučili u prethodnoj sekciji!  
        Umjesto broja/stringa/izraza, sada samo upišete `a` (naziv varijable). :)
        """.md,
        image(Images.flowcharts.vars.kvadrat_1.ref, "Varijable"),
        """
        Malo terminologije:
        - **deklaracija** znači "uvođenje varijable", kažemo programu da želimo koristiti tu varijablu.  
        Nakon deklaracije, varijabla nema nikakvu vrijednost spremljenu u sebi!!!
        - **dodjela varijable** (engl. assignment) znači "spremanje vrijednosti u varijablu"
          Varijabli možemo dodijeliti vrijednost više puta!  
          Možemo je mijenjati po želji, koliko god puta nam to treba u programu.
        """.md
      )
    )

  def obimSection =
    Section(
      "Obim kvadrata",
      frag(
        s"""
        Sada ćemo naći obim kvadrata.  
        Za to ćemo napraviti novu varijablu: `Integer obim`, isto kao u prethodnom primjeru.  
        Zatim ćemo joj dodijeliti vrijednost: `a * 4`.  
        Kada pokrenete program, trebali bi dobiti vrijednost `20`.
        """.md,
        image(Images.flowcharts.vars.kvadrat_2.ref, "Obim kvadrata")
      )
    )

  def povrsSection =
    Section(
      "Površina kvadrata",
      frag(
        """
        Na kraju, još da izračunamo površinu i to je to!  
        Za to koristite novu varijablu: `Integer povrsina`.  
        Naravno, formula je `a * a`
        
        Pošto smo već naučili kako se štiklaju stringovi i varijable, možemo ispisati rezultat u malo ljepšem formatu:  
        ```vbnet
        "a=" & a & " obim=" & obim & " povrsina=" & povrsina
        ```
        """.md,
        image(Images.flowcharts.vars.kvadrat_3.ref, "Površina kvadrata")
      )
    )

}
