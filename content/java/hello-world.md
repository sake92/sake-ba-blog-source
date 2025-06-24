---
title: Hello World
publish_date: 2018-02-04 00:00
---

# Hello World

Vjerovatno vam je do sada dodijao JShell i kucanje u konzoli... :D  
Sada ćemo vidjeti kako se ustvari pokreću Java programi.  
Sastoji se iz 2 dijela: kompajliranje i pokretanje (en. run).  

> Napomena: ovaj dio nije obavezan, ali pomaže za razumijevanje procesa.  
> Možete preskočiti na sekciju Implementacija ako želite.

---
Kompajler (en. compiler) je program koji provjerava sintaksu našeg programa,
da li se poklapaju tipovi itd. te nam **prijavljuje greške** ako ih pronađe.  
I to sve prije nego smo i pokrenuli naš program! Predobro! :D  
Tako smo sigurni da se bar **neke greške neće pojaviti** u programu.  
Javin kompajler se zove `javac` (skraćeno od Java compiler).  

Ulaz u kompajler je izvorni tekst programa (en. source code) 
koji smo napisali (obični tekstualni fajlovi s nastavkom `.java` ),
a izlaz  su fajlovi koji sadrže izvršni kod, koji se može pokrenuti nekako, izvršiti (to su `.class` fajlovi).

Idući korak je pokretanje programa.  
Java programi se sastoje od `bytecode` naredbi (kod nezavisan od procesora i operativnog sistema),
koje su vrlo slične mašinskom kodu (specifičnom za svaki procesor).  
Izlaz iz kompajlera, `.class` fajlovi sadrže te `bytecode` naredbe,
i njih izvršava tzv. JVM (Java Virtuelna Mašina).  
JVM je ustvari `java` program.


![Kompajliranje i pokretanje Java programa](/images/java/compiler.gif){width=600}


## Implementacija
Da vidimo napokon kako izgleda obavezni "Hello World" primjer:
```java
class HelloWorldApp {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

Kada se pokrene prethodno prikazani program dobićemo sljedeći rezultat u konzoli: 
```shell
Hello World!
```


## Kompajliranje i pokretanje programa

Sadržaj programa sačuvajte u fajl `HelloWorldApp.java`.
Primijetite da se **mora zvati isto kao i klasa**, s nastavkom `.java`!  
Kod mene se ovo sve nalazi u folderu `C:\programiranje`.  
Kucamo sljedeće:
```shell
C:\programiranje> javac HelloWorldApp.java
C:\programiranje> java HelloWorldApp
Hello World!
```

To je to, sada možemo nastaviti dalje.  
Svaki primjer koji budemo radili možete isprobati, nemojte mi slijepo vjerovati na riječ da fercera. :)




## Komentari

Komentari su tekst koji ne utiče na rezultat programa.  
Koriste se samo radi objašnjavanja koda, nekom drugom ili nama kad budemo čitali kasnije.  
Prva vrsta komentara se piše sa 2 kose crte `//` (en. slash) i vrijedi **do kraja linije**.  
Druga vrsta je višelinijska (en. multiline) i počinje s `/*` a završava s `*/`.

Npr:
```java
// komentar1
    
/*
    * komentar2
    */

class HelloWorldApp {
    ...
```
