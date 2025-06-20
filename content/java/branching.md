---
title: Uslovno grananje
publish_date: 2018-2-4 00:00
---

# Uslovno grananje

Kompjuteri obično izvršavaju naredbe jednu nakon druge tj. sekvencijalno.  
Međutim, potrebne su nam i naredbe za **uslovno grananje**.  
To jest, kada želimo izvršiti nešto samo pod datim **uslovima**.  
U Javi za ovu svrhu imamo 2 naredbe: `if` i `switch`.


## If naredba

Grananje pomoću `if` naredbe je vrlo jednostavno.
Ako je **uslov** ispunjen onda će se taj dio koda izvršiti.
U suprotnom program nastavlja dalje.

Ovo "uslov ispunjen" može biti DA ili NE, tj. ISTINA (en. true) ili NEISTINA (en. false).
Ovaj **tip podatka** se naziva **Boolean** po matematičaru George Boole-u.

Ako se sjećate logike iz matematike, tablica istine, "I", "ILI", "NE" i ostalih, TO JE TO! :D
- Logička operacija "I" (en. and) se u Javi piše sa `&&`
- Logička operacija "ILI" (en. or) se u Javi piše sa `||`
- Logička operacija "NE" (en. negate, not) se u Javi piše sa `!`, 
i piše se prije uslova koji negira, npr. `!small`.

---
Primjer:
```java
double temperatura = -3;
boolean uslovHladno = temperatura < 5;
if (uslovHladno) {  // može i "if(temperatura < 5)"
    System.out.println("Obuci se, studeno je pravo!");
}
System.out.println("Temperatura je: " + temperatura);
```

Navedeni primjer ispisaće sljedeće:
```bash
Obuci se, studeno je pravo!
Temperatura je: -3
```

Ako izmijenimo temperaturu na `20`, vidjećemo da se prvi `println` neće izvršiti,
jer uslov nije ispunjen:
```bash
Temperatura je: 20
```

---
Da vidimo još jedan primjer:
```java
double temperatura = 21.33;
boolean uslovToplo = temperatura > 25;
if (uslovToplo) // može i "if(temperatura > 25)"
    System.out.println("Toplo je");
else
    System.out.println("Nije toplo");
```

Prethodni kod će ispisati "Nije toplo", jer uslov `uslovToplo` **nije ispunjen**, 
tj. izraz `21.33 > 25` je `false`.  
Ovo `if else` se tretira kao **jedna naredba**!  
Ako želimo da izvršimo više od jedne naredbe u `if` ili `else` bloku,
samo ih okružimo zagradama: `if (uslov) { naredbe1... } else { naredbe2... }`.

Nakon linije 6 program nastavlja normalno sa svojim izvršenjem.  

---
Možemo imati i više uslova u jednoj naredbi, tj. niz `if-elseif-elseif-...-else`.  
Ovi uslovi bi trebali biti **međusobno isključivi** tj. smisleni.
Ako napišete  
`if (broj>5) { } else if (broj>7) { }` to nema puno smisla jer
ako broj nije veći od 5, ne može biti veći od 7, nema šansone. :D  
Ako jeste veći od 5, biće izvršen prvi blok, ali drugi blok koda NIKAD NEĆE BITI IZVRŠEN!

Ako postoji grana `else`, ona će biti izvršena **ako nijedan uslov nije ispunjen**.
Ako ne postoji `else` i nijedan uslov nije ispunjen, ništa neće biti izvršeno od te cijele if naredbe.






## Switch naredba

Grananje pomoću `switch` naredbe može učiniti kod dosta preglednijim.  
Ova naredba se koristi umjesto mnoštva if-else grana, 
od koje svaka grana provjerava **je li varijabla jednaka** nekoj vrijednosti.  
U Javi možete "switchati" cijele brojeve, karaktere, stringove i enumeracije.

```java
int i = 5;
switch (i) {
    case 3:
        System.out.println("Tri");
        break;
    case 5:
        System.out.println("Pet");
        break;
    default:
        System.out.println("Ne znam...");
        break;
}
```

Na liniji 2 ispituje se čemu je **jednaka vrijednost varijable `i`**, 
**redom odozgo** naravno.  
Prvi slučaj (en. case) koji bude ispunjen će biti izvršen.  
Ako je vrijednost varijable `i` broj 5, ispisaće se na ekran "Pet".

Često ne znamo koliko ima mogućih slučajeva, 
pa onda trebamo odlučiti šta da uradimo po tom pitanju.  
Za to nam služi ključna riječ `default`. To je ustvari ona `else` grana u `if` naredbi! ;)


<!-- TODO switch -> expression -->