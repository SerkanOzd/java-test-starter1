# STE Test Starter Package

Unsere Struktur für den Java Test Starter analog unseres Blogposts zu einer wiederverwendbaren Testarchitektur

### Reporting

After each test run a report is created using the [Allure Framework](https://docs.qameta.io/allure/). It is hosted for
this example project with [Gitlab Pages](https://docs.gitlab.com/ee/user/project/pages/) under the following
URL: https://ste.pages.maibornwolff.de/java-test-starter/.

### Selenium GUI Tests

In order to run the GUI tests you need a running Docker since the tests are
using [Testcontainers](https://www.testcontainers.org/modules/webdriver_containers/) to start Chrome and the
Chromedriver. You can either extend the `GUIBaseTest` class
or [register](https://junit.org/junit5/docs/current/user-guide/#extensions-registration-programmatic)
the `ChromeBrowserExtension` manually to get the Webdriver.

### DB Client

For the db client [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference) is used.\
In order to connect to a database, you have to configure the connection in the `persistence.xml` under
'src/main/resources/META-INF'. Here you can edit the existing persistence unit, or you can create new ones which are
then used to communicate with different databases.\
If you have configured a persistence unit, you must create a database class under 'src/main/java/database/utils' or edit
the existing `Database`. This class will be used in the tests to persist entities and to get entities from the database.
For that you have to create methods which use the persistence unit to communicate with the database.\
Besides this, you have to create entity classes like `Student` or `Subject` under 'src/main/java/database/entity' which
map java objects to database tables. After creating such a class you also have to add it to the persistence unit in
the `persistence.xml`.

## Sonarqube analysis

Run the analysis with the following
command: `mvn sonar:sonar -Dsonar.host.url=https://codeanalysis.maibornwolff.de/ -Dsonar.qualitygate.wait=true -Dsonar.login=9278d3d8917762ae0a508267deed0535a5c862df`
.

The analysis report can be found
under: https://codeanalysis.maibornwolff.de/dashboard?id=de.maibornwolff%3Ajava-test-starter

### Ideas and ToDos

* core -> als Library in einem eigenen Projekt abbilden und via Artifactory ablegen.
	* DBClient - Patrick mit kleinem Beispiel und DB mit Testcontainer

	* HttpClient - restassured oder anderes - Patrick mit kleinem Beispiel
	* GraphQLClient
	* Helper
		* Datum und Zeit
		* Filesystem
		* Umgebungsinformationen
		* TestDataGenerator
			* IBAN
			* Email
        * SeleniumHelper…
		* Reporting - allure - Patrick
		* Testcontainer
		* Kafka
* projektspezifics
    * Page Object Models
    * …
* tests
    * Konkrete Tests
    * Ggf. Cucumber Glue Code
