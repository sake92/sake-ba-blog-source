package files.posts.programiranje.flowcharts

import java.time.LocalDate

import scalatags.Text.all._
import utils.Imports.*

object RecursiveFunctions extends FlowchartsTemplate {

  override def pageSettings =
    super.pageSettings
      .withTitle("Rekurzivne funkcije")
      .withLabel("Rekurzivne funkcije")
      .withDescription(
        "Definisanje rekurzivne funkcije. Vizuelno programiranje, flowrun, flowchart."
      )

  override def blogSettings =
    super.blogSettings
      //.withCreatedDate(LocalDate.of(2022, 4, 25))
      .withSections(uvodSection)

  def uvodSection = Section(
    "Uvod",
    frag(
      """
      Sada se pripremite za nešto od čega će vas zaboljeti glava.  
      > Nemojte se brinuti ako ne "skopčate" odmah ovaj koncept, čak ni neki dugogodišnji programeri "nisu načisto" sa rekurzijom. :)  

      Funkcije mogu pozivati funkcije! Da.. to smo već vidjeli, "main" je pozvao funkciju "kvadrat".  
      Ali šta ako funkcija pozove samu sebe!??  
      Da, i to je moguće, i vrlo korisno u nekim slučajevima.


      ---
      Napisaćemo rekurzivnu funkciju koja odbrojava od `x` do `1`:
      - dodajte novu funkciju `countdown`
      - dodajte joj parametar `Integer x`
      - dodajte `If` sa uslovom `x > 0`
      - ispišite `x` u true grani
      - pozovite `countdown(x-1)` nakon ispisa
      - pozovite `countdown(5)` u main funkciji

      """.md,
      div(
        div(cls := "flowrun-instance flowrun--editable flowrun-layout-f-d-o")()
      ),
      br,
      """

      Pokrenite program, ispisaće 5, 4, 3, 2, 1.  
      Kako ova magija radi??

      Kada je `countdown` pozvana sa argumentom 5, ispisaće ga i pozvati `countdown(4)`.  
      Zatim će ispisati 4 i pozvati `countdown(3)`.  
      Zatim će ispisati 3 i pozvati `countdown(2)`.  
      Zatim će ispisati 2 i pozvati `countdown(1)`.  
      Zatim će ispisati 1 i pozvati `countdown(0)`.    
      U ovom trenutku **uslov će biti false** tako da će se funkcija završiti.

      Tek kada se ovaj poziv funkcije završi, kreće se sa "odmotavanjem":
      - završiće se poziv `countdown(1)`
      - završiće se poziv `countdown(2)`
      - završiće se poziv `countdown(3)`
      - završiće se poziv `countdown(4)`
      - završiće se poziv `countdown(5)`
      - završiće se `main`

      Najbitniji dio je **bazni/završni slučaj** tj. uslov završetka rekurzije.  
      To je ova false grana, jer npr. kad ne bi imali ovaj If uslov, rekurzija se nikad ne bi završila.  
      U tom slučaju program bi se zaledio i morali bismo ga zaustaviti (klikom na Stop dugme).  
      Možete probati pozvati main u mainu, doći će do **beskonačne rekurzije**.

      """.md
    )
  )

}
