---
title: For petlja
publish_date: 2022-4-24 00:00
layout: flowrun
---


# For petlja


Ova naredba je malo komplikovanija.  
For petlja sadrži sljedeće dijelove:
- naziv varijable
- početna vrijednost - start
- krajnja vrijednost - end
- inkrement/korak - step
- tijelo/naredbe

Izvršavanje For petlje je isto kao sljedeće:
- Deklarisanje `i: Integer` sa početnom vrijednošću
- While petlja sa uslovom `i <= end` i tijelom
- Assign `i = i + step` na kraju While

---
Napravićemo program isti kao za While petlju, printanje prvih 5 brojeva:
- deklarišite For petlju sa varijablom `i`
- početna vrijednost je 1
- krajna vrijednost je 5
- korak je 1
- ispišite `i` u tijelu For petlje

Postigli smo isti rezultat kao sa While, samo sa dosta manje koda!  
Plus, ovako je dosta čitljivije.

<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>


## Vježbe
1. Ispišite svako 5-i broj od 0..50
1. Ispišite tablicu množenja od 1 do 10
1. Izračunajte faktorijel broja 7