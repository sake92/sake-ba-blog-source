---
title: While petlja
publish_date: 2022-4-24 00:00
layout: flowrun
---


# While petlja


Kada želimo da ponovimo dio koda više puta, uvijek možemo copy-paste te naredbe.  
Naravno, to nije uvijek efikasno niti moguće.  
Npr. kada želimo ponoviti dio koda *pod nekim uslovom*, može biti 0 ili n puta..  
Za to nam služi While naredba/petlja.

While ima 2 dijela:
- uslovni izraz - provjerava se svaki put prije izvršavanja tijela petlje
- tijelo (engl. body) - naredbe koje se izvršavaju kada je uslov ispunjen

---
Napravićemo program koji ispisuje brojeve od 1 do 5:
- deklarišite `Integer x = 1`
- dodajte `While` sa uslovom `x <= 5`
- ispišite `x` u tijelu While
- dodijelite `x = x + 1` nakon ispisa

Kada pokrenete program trebali bi vidjeti brojeve 1,2,3,4,5 ispisane na izlaznom panelu.

Primijetite da smo u dodjeli `x = x + 1` koristili *prethodnu vrijednost x-a*!  
U izrazu `x+1`, `x` se odnosi na zadnju vrijednost *prije ove naredbe*.


<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>


## Vježbe
1. Ispišite brojeve od 1..20 koji su djeljivi sa 3
1. Za uneseni n ispišite sljedeće drvo (primjer za n=4):  
1  
22  
333  
4444  
        
