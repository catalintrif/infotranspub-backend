#nutch-plugin
Plugin pentru crawler-ul [Apache Nutch](http://nutch.apache.org), care scrie intr-o tabela toate URL-urile nou gasite
sau modificate. Crawler-ul porneste de la o lista initiala (redusa) de URL-uri, definita in fisierul urls/seed.txt.

Nutch trebuie instalat separat (eventual pe alta masina).
Trebuie doar sa aiba acces la baza de date a aplicatiei de administrare (am inteles ca se lucreaza la ea) pentru a pune URL-urile modificate intr-o tabela.
Recomandat ar fi ca activitatea de crawling sa fie programata noaptea (dureaza cateva ore si e network intensive).
Pentru site-urile cu multe PDF-uri dureaza mai mult, de exemplu pentru Pitesti lucreaza cel putin 30 min pentru ca descarca PDF-urile sa le recalculeze checksum si face pauze intre request-uri ca sa nu fie blocat de server.
Partea buna e ca merge pe toate domeniile in paralel, deci nu astepti sa termine Pitesti ca sa inceapa alt site. Dar asta inseamna ca e destul de network intensive pe serverul unde e instalat.

Ca optiuni de deployment, ar fi doua:

1. Nutch poate fi rulat din linie de comanda, periodic de un scheduler (recomandarea mea).
2. Se poate porni ca server si expune un API REST, insa am observat ca nu este la fel de stabil si in plus ar ocupa resurse tot timpul.

##Instalare
* Download distributie binara [Nutch 1.12](http://www.apache.org/dyn/closer.lua/nutch/1.12/apache-nutch-1.12-bin.tar.gz)
* Dezarhivare (de preferat in directorul home):
```
tar -xvf apache-nutch-1.12-bin.tar.gz
```
* Setare JAVA_HOME (exemplu OSX):
```
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home
```
* Verificare / modificare locatie instalare Nutch (proprietatea nutch.home) in pom.xml pentru a copia corect resursele
dupa build:
```
    <nutch.home>${env.HOME}/apache-nutch-1.12</nutch.home>
```
* Build proiect (include copiere configurari Nutch si plugin-ul):
```
mvn install
```
* Copiere driver JDBC in directorul plugin-ului:
```
cd ~/apache-nutch-1.12
cp ~/.m2/repository/org/postgresql/postgresql/9.4.1212/postgresql-9.4.1212.jar plugins/indexer-db/postgresql.jar
```
* (Optional) Pentru a rula in mod development (crawl doar pe masina locala):

1. Se adapteaza calea catre proiectul local din fisierul src/test/resources/test-seed.txt
2. Se inlocuieste config/urls/seed.txt cu fisierul test-seed.txt modificat
* Lansare crawler:
```
./run.sh
```
