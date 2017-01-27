Flux de lucru pentru o agentie:
* Modificare date agentie (cele din GTFS + coordonate pentru centrare harta) 
* Adaugare linie noua
    * Apare un popup ce permite definirea datelor pentru o linie (minimal: nume, tip; optional: descriere...)
    * Se salveaza linia si este adaugata in lista de selectie
    * Sensul este implicit Tur (tab-ul de sub selectorul de linie)
    * Pentru linia si sensul curente, se incepe definirea statiilor, prin clic pe harta:
        * Clic pe harta -> Apare popup pentru definirea unei statii noi (date minimale: nume). Este evidentiata linia pentru care se defineste statia.
        * Statia este salvata si adaugata la lista de statii ale liniei curente.
        * Pe harta e actualizat traseul intre statiile definite, cu indicarea sensului.
        * Se ajusteaza traseul prin drag pe linia conectoare 
    * Se comuta pe Retur
        * Statiile sunt copiate automat de la Tur dar in sens invers.
        * Se poate adauga o statie noua
            * Numarul de ordine al statiei este implicit N+1 dar poate fi modificat
        * Se poate sterge o statie
            * Daca statia este folosita si de alte linii, se sterge doar asocierea cu linia curenta
        * Pe harta e actualizat traseul intre statiile din lista, cu indicarea sensului.
    * Definire calendare
        * Implicit sunt create 2 calendare: Zile lucratoare si Zile nelucratoare 
        * Se deschide calendarul Zile lucratoare
            * Se poate modifica lista de zile ale saptamanii (ex: adaugare Sambata) prin grup checkbox
            * Se selecteaza tab Tur sau Retur 
            * Apare un tabel unde capul de tabel contine statiile in ordine
            * Fiecare rand reprezinta o cursa (numar generat secvential)
            * Fiecare celula poate contine timpul de trecere
            * Daca o stație nu are stabilit timpul de trecere (dar face parte din cursa) celula este marcata cu >
            * Daca o stație nu face parte din cursă atunci celula este marcată NU

              Cursa | Stația 1 | Stația 2 | Stația 3
              ----- | -------- | -------- | --------
              1     |    NU    |   06:16  |    >
              2     |   07:03  |     >    |    >
              3     |   14:05  |     >    |    NU
            
            * Daca nu se poate reprezenta cursa pe tabelul principal (statiile sunt in alta ordine), se creaza un traseu alternativ
                * Capul de tabel se copiaza si poate fi reordonat prin drag
                * O statie poate fi stearsa (ea ramane pe traseul principal)
                * O noua statie poate fi definita prin clic pe harta (ea nu e adaugata si la traseul principal)

                  Cursa | Stația 1 | Stația 2 | Stația 3
                  ----- | -------- | -------- | --------
                  1     |    NU    |   06:16  |    >
                  2     |   07:03  |     >    |    >
                
                  Traseu alternativ:
                  
                  Cursa | Stația 1 | Stația 3 | Stația 4 | Stația 2
                  ----- | -------- | -------- | -------- | --------
                  3     |   14:05  |     >    |    >     |    >
                         
        * Se deschide calendarul Zile nelucratoare (Sambata, Duminica si Sarbatori legale)
            * Daca una din zile (ex: Sambata) a fost adaugata la alt calendar ea nu mai e disponibila pentru selectie 
            * Se pot face aceleasi operatii pe calendar
        * Se creaza un nou calendar
            * Pot fi selectate doar zilele saptamanii care nu au fost incluse in alt calendar
        

