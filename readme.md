# STE Test Starter Package

Unsere Struktur für den Java Test Starter analog unseres BLogposts zu einer wiederverwendbaren Testarchitektur

* core -> als Library in einem eigenen Projekt abbilden und via Artifactory ablegen.
    * DBClient - Patrick mit kleinem Beispiel und DB mit Testcontainer
	    
	* HttpClient - restassured oder anderes - Patrick mit kleinem Beispiel
			GraphQLClient
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
