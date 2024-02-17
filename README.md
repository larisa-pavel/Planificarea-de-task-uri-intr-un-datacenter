# tema2

Mi-a luat aproximativ 2 zile sa fac tema.

Modul in care am procedat:

    Prima data am scris in clasa MyDispatcher politicile de planificare urmand
instructiunile prezentate in cerinta temei. Mi se pare important de mentionat
modul cum am gandit SQ si LWL: am luat o variabila pentru a retine nodul care
are numarul de task-uri, in executie si in asteptare, din coada, de dimensiune
minima / durata totala de calcule, ramase de executat, minima. Am trecut prin
fiecare nod si am comparat proprietatile, specifice fiecarei politici, ale
nodurilor cu proprietatile nodului retiunut in variabila "final_id"/ "final_id2"
si in momentul in care gaseam un nod cu proprietatea strict mai mica, inlocuiam
valoarea variabilei locale cu valoarea noului nod gasit, iar la final adaug
task-ul pe nodul gasit in variabila locala.
    In clasa MyHost am creat initial o coada normala pentru a retine task-urile
dintr-un nod, si o variabila booleana cu cajutorul careia verific daca am
primit comanda de shutdown. In metoda run, cat timp nu primesc shutdown,
sincronizez coada pentru a nu avea conflicte la accesarea ei, ma uit la primul
element din coada si daca acesta nu e null (avem task-uri in caoda), rulez
task-ul 100 de ms, scad timpul ramas de executare a task-ului si daca acesta
e mai mic sau egal cu 0, atunci il termin si il scot din coada. Celelalte
metode din aceasta clasa le-am implementat tinand cont ca variabila in care
imi retin task-urile este o coada.
    Dupa ce am facut toate aceste clase, am realizat ca o sa am nevoie de un
comparator pentru a verifica mereu, ca atunci cand adaug sau scot task-uri din
coada, daca ordinea task-urilor este corecta, in functie de prioritatea si
preemtarea task-urilor, asa ca am creat clasa QueueComparator, in care am
definit o metoda custom de comparare task-urilor. In metoda "compare", am
abordat urmatoarele cazuri:
    -daca timpul de executie al task-ului 2 este identic cu timpul ramas de
executie al acestuia, inseamna ca task-ul nu ruleaza, deci sortez taskurile
dupa prioritate si timpul la care au sosit in nod.
    -daca task-ul 2 este preemptibil, sortez task-urile dupa prioritate.
    -daca niciuna din conditiile de mai sus nu a fost indeplinita, atunci nu
voi schimba ordinea task-urilor.



      ____          ___
  _.-|   |       __/_  `.  .-"""-.
 {   |   |       \_,` | \-'  /   )`-')
  "-.|___|        "") `"`    \  ((`"`
   .--'-`-.      ___Y  ,    .'7 /|
 .+|______|_____(_,___/...-` (_/_/

 