---
title: If naredba
publish_date: 2022-04-24 00:00
layout: flowrun
---


# If naredba


Već smo vidjeli kako program izvršava naredbe jednu nakon druge, odozgo ka dole.  
Ali uglavnom će nam trebati i neko *uslovno izvršavanje*, program će morati "donositi odluke" tokom izvršavanja.  

Napravićemo program koji će ispisati da li je broj pozitivan ili negativan:
- deklarišite `x: Integer`
- odradite unos `x`
- dodajte `If` blok s vrijednošću `x > 0`
- u `true` grani ispišite "pozitivan"
- u `false` grani ispišite "negativan"

Npr. ako korisnik unese broj 9, izlaz će ispisati "pozitivan".  
Ako korisnik unese -57 ispisaće "negativan".

Izraz `x > 0` ima tip `Boolean`. Boolean može imati vrijednost `true` ili `false`.


<div>
    <div class="flowrun-instance flowrun--editable flowrun-layout-d-o"></div>
</div>



## Komplikovaniji uslovi

### I operator
U gornjem primjeru koristili smo izraz `x>0` koji je Boolean.  
Šta ako nam treba neki komplikovaniji izraz?  
Npr. ako želimo povjeriti je li broj pozitivan i manji od 50.  
Za to nam treba operator "I" (engl. "and"), koji pišemo kao `&&`.  
Dakle, napisali bi `x>0 && x<50`.

Da se podsjetimo "tablice istinitosti" za I operaciju:

<div class="pico">
<table class="striped">
<thead>
<tr>
<th>A</th>
<th>B</th>
<th>A &amp;&amp; B</th>
</tr>
</thead>
<tbody><tr>
<td>true</td>
<td>true</td>
<td>true</td>
</tr>
<tr>
<td>true</td>
<td>false</td>
<td>false</td>
</tr>
<tr>
<td>false</td>
<td>true</td>
<td>false</td>
</tr>
<tr>
<td>false</td>
<td>false</td>
<td>false</td>
</tr>
</tbody></table>
</div>

Vidimo da je uslov ispunjen samo kada su *oba uslova ispunjena*.

### ILI operator
Kada imamo 2 moguća uslova, od kojih nam bilo koji odgovara, 
tada koristimo operator "ILI" (engl. "or"), koji pišemo kao `||`.  
Npr. `x=="abc" || x=="ABC"` izraz kaže da `x` mora biti jednak "abc" ili "ABC".  
Ako je bilo koje od ovo dvoje ispunjeno, i cijeli izraz biće ispunjen/istinit.

<div class="pico">
<table class="striped">
<thead>
<tr>
<th>A</th>
<th>B</th>
<th>A || B</th>
</tr>
</thead>
<tbody>
<tr>
<td>true</td>
<td>true</td>
<td>true</td>
</tr>
<tr>
<td>true</td>
<td>false</td>
<td>true</td>
</tr>
<tr>
<td>false</td>
<td>true</td>
<td>true</td>
</tr>
<tr>
<td>false</td>
<td>false</td>
<td>false</td>
</tr>
</tbody>
</table>
</div>

### NE operator

Na kraju, imamo i operaciju negacije, koja samo "obrne" vrijednost Booleana.  
Na bosanskom se čita "NE" (engl. "not"), a pišemo je kao `!`.  
Ovaj operator piše se *prije vrijednosti koju negiramo*, npr. `!uslov`.

<div class="pico">
<table>
<thead>
<tr>
<th>A</th>
<th>!A</th>
</tr>
</thead>
<tbody>
<tr>
<td>true</td>
<td>false</td>
</tr>
<tr>
<td>false</td>
<td>true</td>
</tr>
</tbody>
</table>

</div>



