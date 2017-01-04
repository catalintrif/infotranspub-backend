Proiectul DBTools contine unelte pentru importul fisierelor GTFS in baza de date SQL
Orice fisier GTFS de pe http://transitfeeds.com/feeds poate fi importat in baza prin dbtools

Pentru moment, fisierul de import  trebuie sa se numeasca sfmta.gtfs si sa fie in folderul in care se afla pom.xml


Procedura de import

Nota: dupa import vor fi create tablele tmp_xxxxx unde xxxxx corespunde fisierului txt din GTFS
De aceea, inainte de a importa fisierul GTFS, trebuie sterse din baza de date tabelele tmp_xxxxxx existente cu comanda 
drop_tmp_tables.sql		

Tabelele tmp_xxx sunt o etapa temporara

Procedura de  import


Pentru (re)creearea tabelelor GTFS in baza 

cd infotranspub-backend  				# urmat de 
cd database/schema	 

psql -d <database> -a -f create_gtfs_tables.sql		# urmat de 
psql -d <database> -a -f populate_gtfs_tables.sql

Pentru stersul tabelelor
psql -d <database> -a -f drop_gtfs_tables.sql		


Obligatoriu pentru import in baza de date si creearea tabelelor tmp_xxxxxxx

cd infotranspub-backend  				# repo
cd database/dbtools					# proiect DBTools
java -cp ./target/dbtools-1.0-SNAPSHOT.jar  ro.gov.ithub.infotranspub.dbtools.app.GTFSFileImportApp	# importa fisierul sfmta.gtfs

Pentru citirea datelor din baza, a se vedea proiectul stoplist (in folderul rest/modules/search)
