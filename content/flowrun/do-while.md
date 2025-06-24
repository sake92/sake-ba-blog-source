---
title: Do While petlja
publish_date: 2022-04-24 00:00
layout: flowrun
---


# Do While petlja


Ova naredba je slična While.  
Glavna razlika je da Do While provjerava uslov **tek nakon što izvrši naredbe**.  
Do While će izvršiti blok naredbi **minimalno jednom**.

---
Napravićemo program koji čita broj od korisnika, koji mora biti pozitivan:
- deklarišite Integer x
- dodajte Do While sa uslovom `x <= 0`
- unesite `x` u tijelu D oWhile

Ovaj program možemo pročitati ovako:  
"Unesi broj x, dok god je manji ili jednak nuli"

Kada pokrenete program probajte unijeti sljedeće vrijednosti: 1, 17, -55, 6.  
Do While će završiti samo kada unesete pozitivan broj.

<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>


## Vježbe
1. Unosite String s i ispišite ga, sve dok ne bude jednak "END"
1. Unosite jednu po jednu ocjenu dok korisnik ne unese `0`. Zatim ispišite prosjek tih ocjena
