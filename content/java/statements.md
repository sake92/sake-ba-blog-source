---
title: Naredbe
publish_date: 2018-04-27 00:00
---

# Naredbe

Naredbe (en. statements) su kao rečenice u govornom jeziku, 
i obično su u imperativnom obliku, kao da se obraćamo kompjuteru:
uradi ovo, izbriši ono, upiši u fajl itd.  
Deklaracije varijabli su naredbe, npr. `int i = 5;`, može se čitati kao  
"napravi varijablu s imenom 'i' i dodijeli joj vrijednost 5! ODMAH!".  
Iako još nismo objasnili funkcije, neke od njih su također naredbe, npr:
```shell
jshell> System.out.println("Hello!")
Hello!
```

Vrlo duga naredba `System.out.println` je funkcija za printanje u konzolu.  
Do sada je JShell to automatski radio za nas, ispisivao vrijednost zadnjeg izraza,
u svom nekom predefinisanom formatu...
Ovako mi možemo ispisati šta god želimo. Primijetite da ispisani `String` nema navodnika!  
Navodnici se samo **koriste za pisanje stringova**!!! Nisu uključeni u samu vrijednost stringa.
Isto je i sa karakterima.

Da vidimo još par primjera:
```shell
jshell> System.out.println("x je " + 5);
x je 5

jshell> String rezultat = "x je " + 5;
rezultat ==> "x je 5"
jshell> System.out.println(rezultat);
x je 5
```

Dakle, možemo proslijediti string direktno, ili varijablu koja je string, svejedno. 🙂  
Kraj naredbe se označava s `;` (tačkazarez, en. semicolon).










