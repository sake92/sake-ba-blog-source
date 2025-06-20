---
title: Instalacija Jave
publish_date: 2017-7-2 00:00
---

# Instalacija Jave

Ako vam je baš mrsko instalirati Javu, postoje i online alternative:
- [JDoodle](https://www.jdoodle.com/online-java-compiler)
- [JShell](https://tryjshell.org/)

Zgodne su za probavanje i igranje sa Javom.

---
Preporučujem da instalirate [Javu 21](https://www.azul.com/downloads/?package=jdk#zulu) (ili noviju verziju).  
Samo downloadujte i raspakujte zip npr. u `C:\Java`.  
Idući korak je dodavanje PATH varijable.

---
Instalacija [Jave 8](https://www.java.com/en/download/) je malo komplikovanija, ali je dostupna i za 32-bitne i 64-bitne računare.        
Naravno, možete instalirati više verzija Jave, ali samo jednu možete dodati u `PATH`!

![JDK download step 1](/images/java/download-jdk-1.jpg){width=1030}


Kada se otvori stranica sa prethodne slike kliknite na dugme **download JDK**.

> JDK (Java Development Kit) su alati za razvijanje Java programa: kompajler, debager itd.
> JDK sadrži JRE (Java Runtime Environment) koji služi za pokretanje Java programa (JVM, Java API klase itd).

Na sljedećoj slici kliknite na "jdk-8u131-windows-x64.exe".  
To je instalacija za 64-bitni Windows.  
Ako slučajno imate 32-bitni Windows (vrlo malo vjerovatno) onda skinite tu verziju... 

![JDK download step 2](/images/java/download-jdk-2.jpg){width=1030}

Preporučujem da Javu instalirate u folder pod nazivom `C:\Java`.  
Ovo ne morate raditi ali je korisno kada vam treba više verzija Jave.  
Također, neki programi na Windowsu imaju problema kada putanja sadrži razmak, 
zato nećemo instalirati u `Program Files`... 

> **Napomena**: Ne trebate instalirati i JRE!  
> Kada vam dođe prozor za JRE kliknite Cancel! (iksić).  
> Upravo sam vam uštedio 100-tinjak megabajta, nema na čemu... :D

Dakle, kada se završi download, otvorite instalaciju i kliknite Next.

![JDK folder](/images/java/install-jdk-folder.jpg){width=500}

Zatim kliknite dugme "Change..." i prepravite putanju foldera na `C:\Java\jdk1.8.0_131`.  
JRE će već biti instaliran zajedno sa JDK! Tako da nam ne treba još jedna instalacija...

![JRE, nope!](/images/java/install-jre-no.jpg){width=500}


## Dodavanje PATH varijabli



Dio koji slijedi vezan je više za sam rad operativnog sistema (Windows u našem slučaju) nego za Javu.  
Otvorite `Computer Properties` na Windowsu (desni klik na My Computer -> Properties). Otvoriće vam se sljedeći prozor:


![Computer Properties](/images/os/windows/computer-properties.jpg){width=780}

Kliknite na Advanced System Settings. Dobićete sljedeći prozor:

![System Properties](/images/os/windows/system-properties.jpg){width=780}


Otvorite `Advanced` tab i kliknite `Environment Variables...`.  
Dodajte 3 sistemske varijable u donji prozor (u System Variables):

| Naziv varijable | Vrijednost |
| --- | --- |
| JAVA\_HOME | C:\\Java\\jdk1.8.0\_131 |
| JDK\_HOME | %JAVA\_HOME% |
| JRE\_HOME | %JAVA\_HOME%\\jre |



**Glavno**, otvorite `PATH` varijablu i dodajte `;%JAVA_HOME%\bin` na kraj.  
Nemojte zaboravit tačkazarez kopirati! :D  
Bez navodnika, naravno! Kliknite Ok, Ok...

To je to! Uspješno ste instalirali Javu! :)  
Stanje bi trebalo biti otprilike ovako:

![System Variables](/images/os/windows/system-variables.jpg){width=780}



## Provjera instalacije

Da bi provjerili jesmo li uspješno obavili zadatak, 
    otvorićemo Command Prompt (kod Linuxaša se rekne Shell).  
Otvorite Start, ukucajte `cmd` i kliknite <kbd>Enter</kbd>. Ukucajte `java -version`.  
Ako dobijete poruku 
    `'java' is not recognized as an internal or external command, operable program or batch file.`
    nešto nije uredu, provjerite sve korake sekcije "Dodavanje PATH varijabli" ponovo!

Ako dobijete nešto slično sljedećoj slici, sve je OK:

![Java verzija](/images/os/windows/cmd-java-version.jpg){width=604}



