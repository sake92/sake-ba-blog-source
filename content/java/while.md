---
title: While petlja
publish_date: 2018-2-4 00:00
---

# While petlja

Kada želimo ponavljati izvršiti neki dio koda više puta,
najjednostavniji način je copy-paste. 
Kopiramo ga n-puta i završeno! :D

Međutim, ovo nije fleksibilno kada kasnije želimo da izmijenimo taj djelić koda.  
Tada moramo izmijeniti sve linije... :/

Zato postoje specijalne naredbe za **ponavljanje**: `while`, `do-while` i `for`.  
Zovu se još i "petlje", jer se vrlo lahko zapetljati u njima.  


U sljedećem primjeru želimo izvršavati kod sve dok je neki **uslov** ispunjen (en. while je "dok").  
```java
int i = 0;
while(i < 10) {
    System.out.println("Cifra i je: " + i);
    i = i + 1;
}
```

Naravno, unutar tog bloka **uslov se mora mijenjati**, inače se program *neće nikad zaustaviti*.  
To se naziva **beskonačna petlja** i obično nije poželjna... :)

Uslov se ispituje u svakoj **iteraciji** petlje. Dakle, svaki put kada se dođe do linije 5 uslov će se ponovo ispitati.
Ako uslov nije zadovoljen, petlja se zaustavlja i program se nastavlja izvršavati na liniji 6.


