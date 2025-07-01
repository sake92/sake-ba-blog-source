---
title: Varijable
publish_date: 2022-04-24 00:00
layout: flowrun.html
---

# Varijable

Varijable nam služe za **spremanje informacija/podataka**.  
Recimo ako računamo prosjeke ocjena učenika (npr. ima 29 učenika trenutno), morali bi napisati broj `29` više puta u programu. 
Ako dođe novi učenik, morali bi na svim mjestima u programu izmijeniti `29` na `30`... 
Varijable nam pomažu da izbjegnemo ovakve situacije.  

Svaka varijabla ima **naziv** i **tip**.  
Tip nam ograničava **šta možemo spremiti u tu varijablu**:
- `Integer` - cijeli broj
- `Real` - realni broj
- `String` - tekst
- `Boolean` - jeste/nije (engl. `true`/`false`)

Napravićemo program koji će izračunati obim kvadrata.  
Za to će nam trebati 2 varijable:
- `a: Integer` - dužina stranice kvadrata
- `obim: Integer` - rezultat


## Deklaracija i dodjela varijable


U donjem prozoru uradite sljedeće:
1. deklarišite varijablu `a`:
    - kliknite na strelicu
    - odaberite `Declare`
    - kliknite na novi Declare blok i unesite naziv `a`.  
        Primijetite da varijabla već ima tip `Integer`.  
        Kasnije ga možemo promijeniti po potrebi.
1. dodijelite vrijednost varijabli `a`:
    - kliknite na strelicu ispod deklaracije `a` varijable
    - odaberite `Assign`
    - kliknite na novi Assign blok i unesite naziv `a` i vrijednost `5`

Ovdje ćemo malo zastati. Prvo probajte ispisati varijablu `a` na ekran.  
To smo naučili u prethodnoj sekciji!  

<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>


Malo terminologije:
- **deklaracija** znači "uvođenje varijable", kažemo programu da želimo koristiti tu varijablu.  
Nakon deklaracije, varijabla nema nikakvu vrijednost spremljenu u sebi!  
(možemo joj dati početnu vrijednost, što je dobra praksa)
- **dodjela varijable** (engl. assignment) znači "dodjela vrijednosti varijabli"  
    Varijabli možemo dodijeliti vrijednost više puta tokom programa!  


## Obim kvadrata

Nastavićemo sa pravljenjem programa za računanje obima kvadrata.  
Dodaćemo još jednu varijablu, `Integer obim` koja će sadržati rezultat, obim kvadrata.  

Naravno, varijablama smo mogli dati bilo koji drugi naziv: `xyz`, `fdgfdgfdxcv` itd.  
Ali varijable moraju imati **smislene nazive**, da bi kasnije mogli lakše razumjeti program.  

Dodijelićemo varijabli `obim` ovu vrijednost: `a * 4`.  
Kada ispišemo `obim` i pokrenemo program, trebali bi dobiti vrijednost `20`.









