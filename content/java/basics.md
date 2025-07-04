---
title: Osnove Jave
publish_date: 2018-03-25 00:00
---

# Osnove Jave

## JShell

Da bi se igrali sa JShellom morate imati instaliranu Javu verzije 9 ili više.  
Ispratite [sekciju za instalaciju](/java/installation.html).  

Koristićemo JShell program za upoznavanje i igranje s Java jezikom.  
Otvorite konzolu (bez ikakvog straha! :D), ukucajte `jshell` i udarite Enter.  
Dobićete tzv. interaktivni shell u kojem možete kucati Javu.  
Zovu ga još i REPL (Read Evaluate Print Loop), jer dobija unos od nas,
evaluira ga, isprinta neki odgovor, i ponavlja ukrug.


## Izrazi i operatori

Za početak, JShell možemo koristiti kao primitivni kalkulator.  
Tako ćemo steći osjećaj kako se ponašaju brojevi u Javi, 
i usput objasniti neke osnovne koncepte i pojmove.   
Ako ukucamo u REPL broj 5 i pritisnemo Enter, vidjećemo sljedeće:

```sh
jshell> 5
$1 ==> 5
```

REPL je razumio i odgovorio nam da je to vrijednost `5`.  
U programiranju se kaže da je broj **izraz**.
*Izraz* u programiranju ima isto značenje kao i u matematici.  
Dakle, misli se na nešto što ima **vrijednost**. 
Nešto što možemo prikazati, s čim možemo baratati.  

---
Ako se opet prisjetimo (dosadne) matematike, sjetićemo se da se simboli
`+`, `-`, `*`, itd. nazivaju **operatori**.   
Pošto smo rekli da je REPL kao kalkulator, da vidimo šta sve možemo uraditi:
```sh
jshell> 2 + 2
$2 ==> 4
jshell> 2 - 5
$3 ==> -3
jshell> 7 * 3
$4 ==> 21
jshell> 9 / 4
$5 ==> 2
```

Izgleda da dijeljenje ne radi kako treba... Našli smo grešku! :D  
Ali, nije tako. Java *podrazumijeva* da želimo podijeliti dva **cijela** broja!  
Ako se sjetimo osnovne škole, `5 / 2` je ustvari `2` **cijelih** i **ostatak** `1`. :)  
Dakle, kada dijelimo dva cijela broja, ustvari radimo "cjelobrojno dijeljenje".

Također, možemo dobiti i ostatak od dijeljenja, pomoću operatora `%`.  
Npr. `5 % 2` je `1`.

---
Kada želimo koristiti **realne** brojeve (sa zarezom), pišemo tačku nakon broja.  
Tačka se koristi umjesto zareza, jer se zarez koristi za neke druge stvari.  
Uglavnom, da vidimo kako rade realni brojevi (en. **float**ing point):

```sh
jshell> 3.0 + 5.1
$6 ==> 8.1
jshell> 7.3 - 2.9
$7 ==> 4.4
jshell> 5.5 * -2.0
$8 ==> -11.0
jshell> 9.0 / 4.0
$9 ==> 2.25
```

Sada se sve čini onako kako treba. :)

---
Redoslijed operacija (en. precedence) radi očekivano.  
Npr. množenje se radi prije sabiranja.  
Ako želimo baš, baš, biti sigurni da će program raditi kako treba, možemo koristiti zagrade.

```sh
jshell> 2 + 2 * 2
$10 ==> 6
jshell> (2 + 2) * 2
$11 ==> 8
```
      
## Varijable
 
Primijetite šta REPL ispisuje kao odgovor, poslije svake unesene linije: `$1 ==> 5`.  
Ako ukucamo `$1` u REPL vidjećemo sljedeće:
```sh
jshell> $1
$1 ==> 5
```

Dakle, REPL nam kaže da je **vrijednost** varijable `$1` jednaka `5`.  
Izraz `5` je spremljen u **varijablu** `$1`, koju možemo koristiti umjesto broja `5`.  
> Šta je to varijabla?  
> Zamislite da imate kutije raznih oblika i dimenzija.  
> Varijabla je baš kao **kutija sa natpisom** koji vi želite.
> Npr. kutija u kojoj čuvamo neki broj.

Kreiraćemo novu varijablu pod nazivom `brojJabuka`, 
za čuvanje `int`egera (en. "integer" je cijeli broj).  
Ovo `int` je tip varijable (veličina i oblik kutije)! 
Kasnije ćemo govoriti malo detaljnije o tipovima.

```sh
jshell> int brojJabuka;
brojJabuka ==> 0
created variable brojJabuka : int
```


Pošto nismo dali vrijednost našoj varijabli, 
Java nam pomaže tako što postavlja `brojJabuka` na vrijednost `0`.  
Obično je to ono što želimo, ali je najbolje postaviti početnu vrijednost **eksplicitno**,
čim uvodimo novu varijablu.
Tako odmah znamo koja joj je početna vrijednost i ne moramo razmišljati o tome... :)  
To radimo ovako:

```sh
jshell> int brojJabuka = 0;
brojJabuka ==> 0
created variable brojJabuka : int
```

Vrlo jednostavno, nakon naziva varijable slijedi znak jednako i vrijednost, ovdje je to `0`.  
Primijetite da ovaj znak jednakosti **nije matematička jednakost** već operacija dodjele!  
Tj. kao da kažemo "postavi varijablu `brojJabuka` na nulu!".

Varijablu možemo izmijeniti, tj. postaviti na novu vrijednost:
```sh
jshell> brojJabuka = 5;
brojJabuka ==> 5
assigned to brojJabuka : int
```

Sada je vrijednost promijenjena na `5`. 
Nakon ove linije, gdje god koristimo `brojJabuka` to je kao da napišemo `5`.

---
Također, možemo iskoristiti **prethodnu vrijednost varijable** da bismo dobili novu:
```sh
jshell> brojJabuka = brojJabuka + 1;
brojJabuka ==> 6
jshell> brojJabuka += 1;
$23 ==> 7
```

U izrazu `brojJabuka + 1`, vrijednost `brojJabuka` odnosi se na **zadnju vrijednost varijable**,
dakle vrijednost prije ove linije, tj. `5`.

Postoji i skraćena verzija za ovo, prikazana na liniji 3, s operatorom `+=`.  
Također imaju skraćene verzije i za oduzimanje, množenje, dijeljenje i modulo: 
`-=`, `*=`, `/=`, `%=` respektivno.  
Ovo "respektivno" je fancy izraz za "u navedenom redoslijedu". :D

### Nazivi varijabli

Nazivi varijabli i tipova (en. identifiers) ne mogu imati razmake u sebi, 
    ne mogu biti nijedna od rezervisanih riječi kao što je `class`, `int`.  
Ne mogu biti neki rezervisani znakovi, kao npr. `,`, `&` itd.  
Ne mogu počinjati brojem.
Primjeri nevalidnih naziva varijabli (isprobajte u JShellu):
- `int my variable = 5`
- `int int = 5`
- `int , = 5`
- `int 5something = 5`

Primjeri validnih naziva varijabli:
- `int _abc = 5`
- `int $xyz = 5`
- `int Z = 5`
- `int t$df_x = 5`

Mogu se koristiti simboli `_` i `$` ali ih treba izbjegavati, 
    pogotovo kao prvo slovo u nazivu varijable!

Preporučeno je da varijable počinju malim slovom,
    da su *camelCase* (svaka iduća riječ počinje velikim slovom), npr. `myVariable`.  

Konstante (varijable koje se ne mogu mijenjati) se pišu velikim slovima 
    i riječi su razdvojene donjom crticom, npr. `MY_CONST`.











