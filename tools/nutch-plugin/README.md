# nutch-plugin
Plugin pentru crawler-ul Apache Nutch (http://nutch.apache.org) care scrie intr-o tabela URL-urile nou gasite sau modificate,
pornind de la lista de seed-uri.

Instalare
* Download distributie binara Nutch 1.12 http://www.apache.org/dyn/closer.lua/nutch/1.12/apache-nutch-1.12-bin.tar.gz
* Dezarhivare (de preferat in directorul home):
tar -xvf apache-nutch-1.12-bin.tar.gz
* Setare JAVA_HOME (exemplu OSX)
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home
* Verificare / modificare locatie instalare Nutch (proprietatea nutch.home) in pom.xml pentru a copia corect resursele dupa build
* Build proiect (include copiere configurari Nutch si plugin-ul)
mvn install
* Copiere driver JDBC in directorul plugin-ului
cd ~/apache-nutch-1.12
cp ~/.m2/repository/org/postgresql/postgresql/9.4.1212/postgresql-9.4.1212.jar plugins/indexer-db/postgresql.jar
* Lansare crawler
./run.sh
