package files.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*

object Variables extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Varijable")
      .withLabel("Varijable")
      .withDescription(
        "Uvod u varijable. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 24))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    """
    Varijable nam služe za **spremanje informacija/podataka**.  
    Recimo ako računamo prosjeke ocjena učenika (npr. ima 29 učenika trenutno), morali bi napisati broj `29` više puta u programu. 
    Ako dođe novi učenik, morali bi na svim mjestima u programu izmijeniti `29` na `30`... 
    Varijable nam pomažu da izbjegnemo ovakve situacije.  
    
    ---
    Svaka varijabla ima **naziv** i **tip**.  
    Tip nam ograničava **šta možemo spremiti u tu varijablu**:
    - `Integer` - cijeli broj
    - `Real` - realni broj
    - `String` - tekst
    - `Boolean` - jeste/nije (engl. `true`/`false`)

    Napravićemo program koji će izračunati obim kvadrata.  
    Za to će nam trebati 2 varijable:
      - `Integer a` - dužina stranice kvadrata
      - `Integer obim` - rezultat
    """.md,
    List(declarationSection, obimSection)
  )

  def declarationSection =
    Section(
      "Deklaracija i dodjela varijable",
      frag(
        s"""
        U donjem prozoru uradite sljedeće:
        1. deklarišite varijablu `a`:
            - kliknite na strelicu
            - odaberite `Declare`
            - kliknite na novi Declare blok i unesite naziv `a`.  
              Primijetite da varijabla već ima tip `Integer`.  
              Kasnije ga možemo promijeniti po potrebi.
        1. dodijelite vrijednost varijabli `a`:
            - kliknite na strelicu ispod deklaracije `a` varijable
            - odaberite `Assign`
            - kliknite na novi Assign blok i unesite naziv `a` i vrijednost `5`
        
        Ovdje ćemo malo zastati. Prvo probajte ispisati varijablu `a` na ekran.  
        To smo naučili u prethodnoj sekciji!  
        """.md,
        div(
          div(cls := "flowrun-instance flowrun--editable flowrun-layout-d-o")()
        ),
        br,
        """
        Malo terminologije:
        - **deklaracija** znači "uvođenje varijable", kažemo programu da želimo koristiti tu varijablu.  
        Nakon deklaracije, varijabla nema nikakvu vrijednost spremljenu u sebi!  
        (možemo joj dati početnu vrijednost, što je dobra praksa)
        - **dodjela varijable** (engl. assignment) znači "dodjela vrijednosti varijabli"  
          Varijabli možemo dodijeliti vrijednost više puta tokom programa!  
        """.md
      )
    )

  def obimSection =
    Section(
      "Obim kvadrata",
      frag(
        s"""
        Nastavićemo sa pravljenjem programa za računanje obima kvadrata.  
        Dodaćemo još jednu varijablu, `Integer obim` koja će sadržati rezultat, obim kvadrata.  

        Naravno, varijablama smo mogli dati bilo koji drugi naziv: `xyz`, `fdgfdgfdxcv` itd.  
        Ali varijable moraju imati **smislene nazive**, da bi kasnije mogli lakše razumjeti program.  
        
        Dodijelićemo varijabli `obim` ovu vrijednost: `a * 4`.  
        Kada ispišemo `obim` i pokrenemo program, trebali bi dobiti vrijednost `20`.
        """.md
      )
    )

  // {"id":"id_fbf74f93_612b_46bf_b7a3_8584d96bec20","name":"Program","config":{"lang":"java","showFunctions":true,"showGenCode":true},"main":{"rawId":"main","name":"main","parameters":[],"tpe":"Void","statements":[{"id":"id_671f2537_441d_41b3_aad6_ab240ed45c92","@type":"Begin"},{"id":"id_ec8789ed_e21e_482d_ae9a_5efe5c57a2bc","name":"a","tpe":"Integer","initValue":null,"@type":"Declare"},{"id":"id_854939da_752d_48ac_ae5d_750ce3fa94aa","name":"a","value":"5","@type":"Assign"},{"id":"id_e8599066_5768_4862_9d76_200a90a97b6c","name":"obim","tpe":"Integer","initValue":"a * 4","@type":"Declare"},{"id":"id_6c1ed49c_51b7_4ed7_8f35_2c05c535f5ec","value":"obim","newline":true,"@type":"Output"},{"id":"id_aeaa760c_0fba_496c_8ca1_33e5af707e65","maybeValue":null,"@type":"Return"}]},"functions":[],"version":"0.1"}
}
