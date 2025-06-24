---
title: FlowRun
publish_date: 2022-04-24 00:00
theme_props:
  pagination_disabled: true
---

# FlowRun

Kompjuteri izvršavaju **naredbe** jednu po jednu, redom.  
Naredbe su logički grupisane u **programe**.  
Program sadrži naredbe koje kompjuter treba izvršiti.

Program se može predstaviti **vizuelno** pomoću **dijagrama toka** (engl. flowchart).  
Koristićemo ugrađeni editor, koji može i pokrenuti program koji napravimo.  

Ako želite da sačuvate svoje programe koje napravite, možete se registrovati na [flowrun.io](https://flowrun.io/).  
Tu možete vidjeti i neke programe koje su napravili drugi korisnici, kliknite [ovdje](https://flowrun.io/explore).

Tutorijali:
{%
set tutorials = [
    { "label": "Osnove", "link": "/flowrun/basics.html" },
    { "label": "Varijable", "link": "/flowrun/variables.html" },
    { "label": "If naredba", "link": "/flowrun/ifs.html" },
    { "label": "While petlja", "link": "/flowrun/while.html" },
    { "label": "Do While petlja", "link": "/flowrun/do-while.html" },
    { "label": "For petlja", "link": "/flowrun/for.html" },
    { "label": "Ugrađene funkcije", "link": "/flowrun/predefined-functions.html" },
    { "label": "Definisanje funkcije", "link": "/flowrun/custom-functions.html" },
    { "label": "Povratna vrijednost funkcije", "link": "/flowrun/return-of-functions.html" },
    { "label": "Rekurzivne funkcije", "link": "/flowrun/recursive-functions.html" }
]
%}

{% for tutorial in tutorials %}
- [{{ tutorial.label }}]({{ tutorial.link }})
{% endfor %}
