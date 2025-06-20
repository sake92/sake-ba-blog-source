---
title: Funkcije
publish_date: 2018-4-5 00:00
---

# Funkcije

Prvo malo terminologije.  
Potprogram (en. subprogram, subroutine, procedure) je **dio programa** koji možemo pozvati po **imenu**.  
Java ih zove... khm... **metode**... :D  
Ja ću najčešće koristiti termin "funkcija" ili "metoda", kako kad.

Funkcijama se mogu proslijediti **argumenti**, tj. ulazni podaci koje funkcija može koristiti.

Slijedi primjer funkcije za sabiranje dva cijela broja:
```java
int suma(int a, int b) {
    return a + b;
}
```

Definicija funkcije ima sljedeće dijelove:
- tip vrijednosti koju **vraća funkcija**. 
Kod nas je to `int`, jer je rezultat sabiranja dva `int`a također `int`.  
- naziv funkcije, kod nas je to "suma"
- lista parametara u zagradama, ovdje su to brojevi: `int a, int b`.  
Ako nema parametara onda idu samo zagrade, npr. `mojaFunkcija()`
- tijelo funkcije u vitičastim zagradama
- ključna riječ `return` i **rezultat** funkcije

Da vidimo u JShellu kako da deklarišemo i pozovemo prethodnu funkciju:
```shell
jshell> int suma(int a, int b) {
    return a + b;
}
created method suma(int,int)
jshell> suma(1, 4)
$6 ==> 5
```

Funkcija se poziva navođenjem njenog imena, i argumenata unutar zagrada.  
Npr. `suma(1, 4)`.  
Pošto poziv funkcije vraća vrijednost `int`, možemo je spasiti u varijablu,  
npr. `int rezultat = suma(1, 4)`.  
Dakle, i poziv funkcije je izraz! Čim ga možemo dodijeliti varijabli. :)








