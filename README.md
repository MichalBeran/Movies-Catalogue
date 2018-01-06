# Movies-Catalogue
[![Build Status](https://travis-ci.org/MichalBeran/Movies-Catalogue.svg?branch=master)](https://travis-ci.org/MichalBeran/Movies-Catalogue)
<br />
Project from PA165, FACULTY OF INFORMATICS MASARYK UNIVERSITY
<br />
[PROJECT WIKI](https://github.com/MichalBeran/Movies-Catalogue/wiki)
<br />
<br />
<br />
GIT:
https://github.com/MichalBeran/Movies-Catalogue

Instructions to run the web app
  1. build the project with mvn clean install
  2. in rest module, run the tomcat7:run command
  3. the application is now accesible at http://localhost:8080/pa165
  4. you can log in as an admin with the email: me@me.me and password: me
  
Instructions to test REST interface
  1. build the project with mvn clean install
  2. in rest module, run the tomcat7:run command
  3. the REST interface is now accesible at http://localhost:8080/pa165/rest
  4. you can test it with curl for example: curl -i -X GET http://localhost:8080/pa165/rest/actors/1 

