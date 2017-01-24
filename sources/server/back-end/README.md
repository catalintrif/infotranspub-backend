REST Server

> Compiling
To compile the Lombok generated boiler-plate code (e.g.: getters and setters), remember to download the Lombok plugin to your IDE: https://projectlombok.org/
The "alfa" application is a REST service, running on Spring. For more information: https://spring.io/guides/gs/rest-service/


> Database connection
Create a local PostgreSQL empty database schema. Pgadmin III can be used for this. By default, the location on the local machine should be localhost:5432
After that, go to the application.properties file, and add the schema name there, along with your username and password for connecting to the schema.


> Running
To start the app, go to the "alfa" project folder:
 cd rest/server/alfa

Build project files:
 mvn install

The necessary files will be produces in the build folder, which in this example is "target". After that, the application can be run with:
 java -jar target/alfa-1.0.jar

An API test call can be made to:
 http://localhost:8080/api/cities
 
Swagger: http://localhost:9000/swagger-ui.html