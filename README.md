# infotranspub-backend
Partea de Back-End a proiectului
Include componentele care ruleaza pe server:
* REST
* importer/exporter GTFS
* route search
* timetable search
* management console
* user session

Mare parte din codul de back-end va fi Java

Setare server Linux

# Instaleaza tool-urile minimale
sudo yum -y install vim
yum -y install screen

#JDK (open source)
yum -y  install java-1.7.0-openjdk-devel

#Maven
yum -y install maven

#git
yum -y install git

# Add project groups
sudo groupadd infotranspub_main
sudo groupadd infotranspub_web
sudo groupadd infotranspub_api
sudo groupadd infotranspub_backend


# Add project admin
sudo adduser <user>  -g infotranspub_main -G infotranspub_web,infotranspub_api,infotranspub_backend

# Add gcc compiler
yum -y install gcc

# Postgres
yum -y install postgresql postgresql.x86_64
yum -y install  postgresql-jdbc
yum install postgresql-server postgresql-contrib
postgresql-setup initdb


systemctl start postgresql
systemctl enable postgresql

# With the user postgres

createdb infotranspub
psql -d infotranspub


Orice fisier GTFS de pe http://transitfeeds.com/feeds poate fi importat in baza
Pentru moment, fisierul de import  trebuie sa se numeasca sfmta.gtfs

Pentru import fisier GTFS

cd infotranspub-backend 
cd database/dbtools
java -cp ./target/dbtools-1.0-SNAPSHOT.jar  ro.gov.ithub.infotranspub.dbtools.app.GTFSFileImportApp  

