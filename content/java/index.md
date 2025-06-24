---
title: Java
publish_date: 2017-07-01 00:00
theme_props:
  pagination_disabled: true
---

# Java

Pretpostavljam da ste prešli [vizuelno programiranje](/flowrun).   
Ako niste, **preporučujem da prvo pređete taj dio**!

Dosta vremena ćemo posvetiti objašnjavanju koncepata i terminologije.  
Terminologija je vrlo bitna, jer ćete kasnije lakše razgovarati o datim konceptima
i brže ćete učiti ostale programske jezike, "povezivati kockice".

Tutorijali:
{%
set tutorials = [
    { "label": "Instalacija", "link": "/java/installation.html" },
    { "label": "Osnove", "link": "/java/basics.html" },
    { "label": "Tipovi", "link": "/java/types.html" },
    { "label": "Naredbe", "link": "/java/statements.html" },
    { "label": "Hello World!", "link": "/java/hello-world.html" },
    { "label": "Uslovno grananje", "link": "/java/branching.html" },
    { "label": "While petlja", "link": "/java/while.html" },
    { "label": "For petlja", "link": "/java/for.html" },
    { "label": "Funkcije", "link": "/java/functions.html" },
    { "label": "Nizovi", "link": "/java/arrays.html" },
    { "label": "Tipovi, opet", "link": "/java/types2.html" },
    { "label": "Klase", "link": "/java/classes.html" },
    { "label": "Opseg", "link": "/java/scope.html" }
]
%}

{% for tutorial in tutorials %}
- [{{ tutorial.label }}]({{ tutorial.link }})
{% endfor %}
