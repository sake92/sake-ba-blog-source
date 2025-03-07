package files.posts.programiranje.java

import java.time.LocalDate
import scalatags.Text.all._
import utils.Imports.*

object Instalacija extends JavaTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Instalacija")
      .withDescription(
        "Postupak downloada i instalacije Java platforme: JDK, JRE, javac, java."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2017, 7, 2))
      .withSections(uvodSection, dodavanjePATHSection, provjeraSection)

  val linkToAddPath =
    a(href:=dodavanjePATHSection.ref)(dodavanjePATHSection.name)

  val installJava8 =
    "http://www.oracle.com/technetwork/java/javase/downloads/index.html"
  val installJava11 = "https://jdk.java.net/11"

  def uvodSection = Section(
    "Instalacija Jave",
    div(
      s"""
        Ako vam je baš mrsko instalirati Javu, postoje i online alternative:
        - [JDoodle](https://www.jdoodle.com/online-java-compiler)
        - [JShell](https://tryjshell.org/)

        Zgodne su za probavanje i igranje sa Javom.

        ---
        Preporučujem da instalirate [Javu 11]($installJava11).  
        Samo downloadujte i raspakujte zip npr. u `C:\\Java`.  
        Idući korak je [dodavanje PATH varijable](${dodavanjePATHSection.ref}).
        
        ---
        Instalacija [Jave 8]($installJava8) je malo komplikovanija, ali je dostupna i za 32-bitne i 64-bitne računare.        
        Naravno, možete instalirati više verzija Jave, ali samo jednu možete dodati u `PATH`!
      """.md,
      img(
        src:=files.images.java.`download-jdk-1.jpg`.ref,
        width:=            1030,height:=            768,alt:="JDK download step 1"
      ),
        
      """
        Kada se otvori stranica sa prethodne slike kliknite na dugme **download JDK**.

        > JDK (Java Development Kit) su alati za razvijanje Java programa: kompajler, debager itd.
        > JDK sadrži JRE (Java Runtime Environment) koji služi za pokretanje Java programa (JVM, Java API klase itd).

        Na sljedećoj slici kliknite na "jdk-8u131-windows-x64.exe".  
        To je instalacija za 64-bitni Windows.  
        Ako znate da imate 32-bitni Windows onda skinite tu verziju... 
       """.md,
      img(
        src:=files.images.java.`download-jdk-2.jpg`.ref,
        width:=            1030,height:=            768,alt:="JDK download step 2"
      ),
      """
          Preporučujem da Javu instalirate u folder pod nazivom `C:\Java`.  
          Ovo ne morate raditi ali je korisno kada vam treba više verzija Jave.  
          Također, neki programi na Windowsu imaju problema kada putanja sadrži razmak, 
            zato nećemo instalirati u `Program Files`... 

          > **Napomena**: Ne trebate instalirati i JRE!  
          > Kada vam dođe prozor za JRE kliknite Cancel! (iksić).  
          > Upravo sam vam uštedio 100-tinjak megabajta, nema na čemu... :D
       """.md,
      p(
        "Dakle, kada se završi download, otvorite instalaciju i kliknite Next.",
        br,
        img(
          src:=files.images.java.`install-jdk-folder.jpg`.ref,
          width:=            500,height:=            380,alt:="JDK folder"
        ),
        """
            Zatim kliknite dugme "Change..." i prepravite putanju foldera na `C:\Java\jdk1.8.0_131`.  
            JRE će već biti instaliran zajedno sa JDK! Tako da nam ne treba još jedna instalacija...
        """.md,
        img(
          src:=files.images.java.`install-jre-no.jpg`.ref,
          width:=            500,height:=            380,alt:="JRE, nope!"
        )
      )
    )
  )

  def dodavanjePATHSection =
    Section(
      "Dodavanje PATH varijabli",
      div(
        p(
          """
             Dio koji slijedi vezan je više za sam rad operativnog sistema (Windows u našem slučaju) nego za Javu.  
             Otvorite `Computer Properties` na Windowsu (desni klik na My Computer -> Properties). Otvoriće vam se sljedeći prozor:
          """.md,
          img(
            src:=files.images.os.windows.`computer-properties.jpg`.ref,
            width:=            780,height:=            570,alt:="Computer Properties"
          ),
          "Kliknite na Advanced System Settings. Dobićete sljedeći prozor: ",
          img(
            src:=files.images.os.windows.`system-properties.jpg`.ref,
            width:=            780,height:=            570,alt:="System Properties"
          )
        ),
        p(
          """
            Otvorite `Advanced` tab i kliknite `Environment Variables...`.  
            Dodajte 3 sistemske varijable u donji prozor (u System Variables):
          """.md,
          table(cls := "table table-hover")(
            tr(th("Naziv varijable"), th("Vrijednost")),
            tr(td("JAVA_HOME"), td("""C:\Java\jdk1.8.0_131""")),
            tr(td("JDK_HOME"), td("%JAVA_HOME%")),
            tr(td("JRE_HOME"), td("""%JAVA_HOME%\jre"""))
          ),
          """
            **Glavno**, otvorite `PATH` varijablu i dodajte `;%JAVA_HOME%\bin` na kraj.  
            Nemojte zaboravit tačkazarez kopirati! :D  
            Bez navodnika, naravno! Kliknite Ok, Ok...

            To je to! Uspješno ste instalirali Javu! :)  
            Stanje bi trebalo biti otprilike ovako:
          """.md,
          img(
            src:=files.images.os.windows.`system-variables.jpg`.ref,
            width:=            780,height:=            570,alt:="System Variables"
          )
        )
      )
    )

  def provjeraSection = Section(
    "Provjera",
    div(
      s"""
        Da bi provjerili jesmo li uspješno obavili zadatak, 
          otvorićemo Command Prompt (kod Linuxaša se rekne Shell).  
        Otvorite Start, ukucajte `cmd` i kliknite <kbd>Enter</kbd>. Ukucajte `java -version`.  
        Ako dobijete poruku 
          `'java' is not recognized as an internal or external command, operable program or batch file.`
          nešto nije uredu, provjerite sve korake sekcije $linkToAddPath ponovo!
      """.md,
      "Ako dobijete nešto slično sljedećoj slici, sve je OK:",
      img(
        src:=files.images.os.windows.`cmd-java-version.jpg`.ref,
        width:=            604,height:=            388,alt:="Java verzija"
      )
    )
  )

}
