---
title: Tipovi
publish_date: 2018-4-5 00:00
---

# Tipovi

Većina ozbiljnih jezika ima **tipove**. One koji nemaju slobodno izbjegavajte! 😉  
Tip je apstrakcija koju dati p.j. razumije. Npr. kada napišemo `int broj`, 
ovo "int" je tip varijable "broj".  
Tako će kompjuter znati, između ostalog, *koliko memorije da rezerviše* za tu varijablu.  
Pošto smo mu dali tu informaciju, onda nam može i ukazati na greške u programu koji pišemo. 
Npr. da ne možemo sabirati broj i slovo, kruške i jabuke...  
Također nas može i usmjeriti, npr. reći nam koje su operacije dostupne nad datim tipom, 
da ne moramo nagađati.

Da vidimo JShell u akciji:
```shell
jshell> int broj = 6;
broj ==> 6
jshell> broj = 4.5;
Error:
incompatible types: possible lossy conversion from double to int
broj = 4.5;
^-^
```

Dakle, Java nam **ne da** da dodijelimo broj `4.5` varijabli koja je tipa `int` (cijeli broj).  
To ima smisla itekako, jer bismo izgubili ovo "zarez pet"... :)  
Ako želimo da budemo pametniji od Jave, i da na silu "utrpamo" `4.5` u `int` to ide ovako:
```shell
jshell> broj = (int) 4.5;
broj ==> 4
```

I šta smo dobili? Samo cijeli dio... Ali, nekad nam ovo baš i treba!  
Uglavnom, pouka je da trebamo slušati šta nam Java poručuje!  
To radi za naše dobro, nju nije briga ako je rezultat netačan, ako je to ono što želimo... :D

---
Naravno, imamo još puno toga reći o tipovima, tek smo "zagrebali površinu"!  
Npr. kako da grupišemo podatke? Kompleksni brojevi, podaci o ljudima, proizvodima itd?  
O tom ćemo pričati u poglavlju o klasama. :)


## Stringovi i karakteri

Prije nego nastavimo dalje, moramo objasniti i stringove, jer se vrlo često koriste.  
String (iz en. nit, kanafa) koristi se za predstavljanje teksta, tj. niza karaktera.  
Karakter (en. *character* je simbol) označava jedan simbol, 
bio to broj, slovo ili neki simbol, emoji...

Stringovi se pišu s **dvostrukim navodnicima**:
```shell
jshell> "Poyy sviete!"
$1 ==> "Poyy sviete!"
```

Stringovi se tretiraju specijalno u Javi, pa imamo i operator "sabiranja" nad njima.  
Npr. možemo "sabrati" 2 stringa, string s brojem i sl:
```shell
jshell> "Ime" + "Prezime"
$2 ==> "ImePrezime"
jshell> "x je " + 5
$3 ==> "x je 5"
jshell> "x je " + 5 + ", i kad se pomnoži s " + 3 + " daje " + (5*3)
$4 ==> "x je 5, i kad se pomnoži s 3 daje 15"
jshell> "strin" + 'g'
$5 ==> "string"
jshell> String s = "ABC"
s ==> "ABC"
```

Ovo sabiranje se naziva *konkatenacija* (en. *concatenate* je nadovezati/spojiti/naštiklati).  
Radi dvosmjerno, možemo sabrati i broj sa stringom, npr. `5 + "hepek.abc"`.  
`String` se piše **s velikim početnim slovom**!

---
`String` u pozadini koristi `char`actere.  
Kao što smo već rekli, `char` je samo jedan karakter.  
Piše se s **jednostrukim navodnicima**:
```shell
jshell> 'a'
$9 ==> 'a'

jshell> ''
Error:
empty character literal
''
^
        'ab'
Error:
unclosed character literal
'ab'
^

jshell> char slovoA = 'A'
slovoA ==> 'A'
```

Dakle, karakter ne može biti prazan, niti može sadržati više karaktera.  
Za ove namjene koristi se `String`! :)


> Važne napomene:
> - string `"1"` nije isto kao broj `1`
> - karakter `'1'` nije isto kao broj `1`
> - string `"1"` nije isto kao karakter `'1'`
> - postoje posebne funkcije za pretvaranje iz jednog tipa u drugi














