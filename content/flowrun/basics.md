---
title: Basics
publish_date: 2022-04-24 00:00
layout: flowrun
---


# Osnove

Svaki program ima svoj **početak** i **kraj**.  
Početak je označen sa `Begin`, kraj je označen sa `End`.  
Strelica pokazuje na **iduću naredbu** koja se treba izvršiti.

Ako kliknemo na dugme `Run` (▶),
program će se **pokrenuti i odmah završiti**!  
Nije se ništa značajno desilo, samo vidimo u output panelu da je program završio.


<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>

Hajde da ispišemo nešto na ekran:
1. kliknite na strelicu, prikazaće se lista naredbi koje možemo dodati
1. kliknite na `Output`
1. kliknite na novu `Output` naredbu
1. upišite `"Pozdrav svijete!"`, sa navodnicima!
1. kliknite Run

Program sada ispisuje poruku `"Pozdrav svijete!"` u izlaznom panelu.  




## Vježbe

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

U prethodnim primjerima ispisivali smo neke jednostavne tekstualne vrijednosti.  
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



