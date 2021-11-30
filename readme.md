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
