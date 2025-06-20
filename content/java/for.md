---
title: For petlja
publish_date: 2018-2-4 00:00
---

# For petlja

Java ima i tzv. `for` petlje. To su haman-ha `while` petlje s finijom sintaksom.  
Sljedeći primjer radi isto kao i prethodni sa `while` petljom.
```java
for (int i = 0; i < 10; i = i + 1) {
    System.out.println("Cifra i je: " + i);
}
```

Uopćena sintaksa for petlje je sljedeća: `for(inicijalizacije; uslov; koraci)`.  
Dijelovi `for` petlje su razdvojeni tačkazarezom.  

Umjesto da deklarišemo varijable prije petlje, zgodno je deklarisati ih u sklopu petlje, 
jer nam poslije petlje one ne trebaju.  
Za to nam služi dio **inicijalizacije**.  

Dio **uslov** je isti kao i kod while petlje.  

Dio **koraci** dolazi **na kraju petlje**, isto kao linija 4 u našoj `while` petlji.  
Koraci obično *utiču na uslov za izlaz* iz petlje.







