The Core Skills Test

Developed by James Oliver for his MSc project.  Created in association with Angus College.

This software requires:

* Glassfish webserver
* Mysql
* Eclipselink implementation of JPA
* The google GSON library
* Netbeans version 7.3.1 or later

Current versions of all libraries are already configured in the project.


Using the system...

* Copy the project WAR file to the appropriate directory in your Glassfish webserver or use Glassfish to import the WAR file.
* Create a JNDI connection pool called CSTConnectionPool that connects to your Mysql database.
* Create a database in the MySQL database called core_skills_test
* Create a JNDI resource called CSTDB that links to CSTConnectionPool

Upon running the web server...

The home page for the 'Staff' component of the website is:

index.jsp

The login page for student access to quizzes is:

login.jsp

