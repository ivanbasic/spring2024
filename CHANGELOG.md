# Changelog

## Version 0.0.28
### new
* Fix tests when Spring security is enabled


## Version 0.0.27
### new
* Spring security enabled
* [Amigoscode](https://www.youtube.com/watch?v=b9O9NI-RJ3o&ab_channel=Amigoscode)

## Version 0.0.26
### new
### Steps:
* 0 Public-key cryptography and Keystore tool. See 0.0.26a_Keystore_Tutorial.md
* 1 Enable HTTPS in server app 
* 2 Consume it using client app and rest template
* 3 TestRestTemplate tests were down after step 1, fixed again after step 2

### Tutorial links, most of them not perfect
* [baeldung create https](https://www.baeldung.com/spring-boot-https-self-signed-certificate)
  * useful: but only the steps 1-3, skip step 4.
* [baeldung invoke with](https://www.baeldung.com/spring-resttemplate-secure-https-service)
  * NOT useful

* [MockMvc vs RestTemplate](https://stackoverflow.com/questions/25901985/difference-between-mockmvc-and-resttemplate-in-integration-tests)
* [MockMvc vs TestRestTemplate](https://www.javaguides.net/2023/12/mockmvc-vs-testresttemplate.html)
* [testresttemplate-to-use-a-keystore](https://stackoverflow.com/questions/56378142/how-to-configure-testresttemplate-to-use-a-keystore)

* [nikola stankovic 1/3 https for java dev](https://medium.com/viascom/practical-ssl-tls-walkthrough-for-java-kotlin-developers-spring-boot-edition-75970a004164)
  *  useful: three possibilities to get public and private key kombo
* [nikola stankovic 2/3 enabling https](https://medium.com/viascom/enabling-https-in-spring-boot-3-c94095389842)

* [how to connect to https](https://stackoverflow.com/questions/75794674/java-spring-boot-how-to-connect-with-server-using-https)
  * baeldung trap for 2K+ developers 

* [this step is missing: how to get .crt from browser](TODO)

* [certificate error](https://stackoverflow.com/questions/3093112/certificateexception-no-name-matching-ssl-someurl-de-found)
  * very useful, localhost trick:
  * When you generate certificate, on question "What is your first and last name?" answer : localhost


## Version 0.0.25
### new
* transactional tests
  * Transactional() for primary ds db1
  * Transactional("DB2") for db2
* [pg sequence 1](https://webkul.com/blog/fix-missing-sequence-table-postgresql/)
* [pg sequence 2](https://stackoverflow.com/questions/60687826/the-increment-size-of-the-sequence-is-set-to-50-in-the-entity-mapping-while-th)

## Version 0.0.23
### update
* refactoring, clean up
* documentation

## Version 0.0.22
### new
* README.md
* clean up

## Version 0.0.21
### update
* repositories. returning... (EmployeeRepo)
  * int (countXYX)
  * entity (findXYZ)
  * dto/record (custom queries)
* repo tests 
* 
* [beeldung jpa java records](https://www.baeldung.com/spring-jpa-java-records)

## Version 0.0.20
### new
* repositories. names of entities

## Version 0.0.19
### new
* repositories. Derived Query, JPQL, Native (EmployeeRepo).
* [1 baeldung Derived Query Methods](https://www.baeldung.com/spring-data-derived-queries)
* [2 baeldung JPQL and Native](https://www.baeldung.com/spring-data-jpa-query)
* [3 spring.io query keywords](https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html)
* [4 oracle HR schema](https://download.oracle.com/oll/tutorials/DBXETutorial/html/module2/les02_load_data_sql.htm)
* [5 error Not a Managed Type](https://www.baeldung.com/spring-data-jpa-not-managed-type-exception)

## Version 0.0.18
### new
* execute schema-db2.sql for database db2 on startup
* [using DataSourceInitializer](https://stackoverflow.com/questions/39280340/how-to-run-sql-scripts-and-get-data-on-application-startup)
* controller test for db2

## Version 0.0.17
### update
* multiple datasource's. databases db1 and db2 
* db2 works, db2 controller/service/repo/table works
* old tests work, but only when postgres db2 is up
* todo: fix tests
* fixed. just wrong setup in application-test.yml for db2
  * can't use the same name for two h2 databases 
* todo: execute schema-db2.sql on startup 

## Version 0.0.16.1
### update
* all tests are actually not working. somehow, h2 is replaced with postgres. hm?
* fixed. just wrong setup in application-test.yml for db1
  * db1 and jdbcUrl instead of url

## Version 0.0.16
### new
* multiple datasource's, for start just database db1
* tutorials, didn't like them
  * [link 1](https://www.baeldung.com/spring-boot-configure-multiple-datasources)
  * [link 2](https://stackoverflow.com/questions/30337582/spring-boot-configure-and-use-two-data-sources)
  * [link 3](https://www.baeldung.com/spring-data-jpa-multiple-databases)
  * [link 4](https://dev.to/javafullstackdev/a-comprehensive-guide-to-multiple-database-configuration-for-microservices-in-spring-boot-1la4)
  * [link 5](https://stackoverflow.com/questions/28275448/multiple-data-source-and-schema-creation-in-spring-boot)
  * [link 6](https://howtodoinjava.com/spring-boot/configure-multiple-datasources/)
  * [link 7](https://pasquale-favella.github.io/blog/15)
  * [link 8](https://www.dineshonjava.com/configure-multiple-databases-spring-jpa-spring-boot/)
* tutorial, didn't like it but finally good namings
  * [multi db](https://www.youtube.com/watch?v=mIFIb_JE47U&ab_channel=AshokIT)
* dialect error when db is not accessible
  * [link](https://stackoverflow.com/questions/78036592/why-cant-spring-boot-deduce-hibernate-dialect)
* url or jdbcUrl
  * [link](https://stackoverflow.com/questions/49088847/after-spring-boot-2-0-migration-jdbcurl-is-required-with-driverclassname) 
* all tests are still working
* database's number 2 and 3, ready for using.
  ```
  docker run --name db2 -e POSTGRES_USER=db2 -e POSTGRES_PASSWORD=db2 -e POSTGRES_DB=db2 -e PGPORT=5002 -p 5002:5002  postgres:13.1
  docker run --name db3 -e POSTGRES_USER=db3 -e POSTGRES_PASSWORD=db3 -e POSTGRES_DB=db3 -e PGPORT=5003 -p 5003:5003  postgres:13.1
  ```


## Version 0.0.15
### new and update
* MAIN. on startup, create schema schema1 in postgres database (call schema.sql) 
  * [just plain wrong: make sure to disable... ddl-auto if you use schema.sql](https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/howto-database-initialization.html)
  * [right one: As of Spring Boot Version 2.7 ... spring.sql.init.mode ](https://stackoverflow.com/questions/49438517/why-spring-boot-2-0-application-does-not-run-schema-sql)

* MAIN. docker command for postgres simplified
  ```
  docker run --name ivan -e POSTGRES_USER=ivan -e POSTGRES_PASSWORD=ivan -e POSTGRES_DB=ivan -p 5432:5432 postgres:13.1
  ```
  
* TEST. on startup, create schema schema1 in H2 database
  * test will call schema.sql from main resources 

* TEST philosophy changed and looks a little bit complicated
  * application-test.yml with h2 properties is still used
  * schema.sql from main resources will be called
  * schema.sql from test resources will be called
  * data.sql from test resources will be called 
  * data-custom.sql script for one test is now part of transaction (it will be rolled back after test)


## Version 0.0.14
### update
* using yaml format for the properties
* [yml for the tests](https://stackoverflow.com/questions/21271468/spring-propertysource-using-yaml)
  * @TestPropertySource doesn't work without password and username 
* log sql commands 
* clean up

## Version 0.0.11
### update
* BeanReader and RepoDemo removed from spring main class
* BeanReader separate 
  * existing spring boot beans 
  * newly created
* clean up  

## Version 0.0.10
### new
#### 1. Controller - Service - Impl - Repo. Postgres database
* Run application with postgres database 
* Tested using pgAdmin and postman.
* Controller - Service - Impl - Repo created for Customer entity

#### 2. integration/controller test. H2 database
* https://www.baeldung.com/spring-testing-separate-data-source
* https://medium.com/@akshatakanaje08/setting-up-h2-for-testing-in-spring-boot-application-7f016220a475
*
* starting from first repo, without new configuration, all old tests expect that postgres database is available. 
* Related to that, see errors in CHANGELOG_DETAILS.
* h2 which will be used for database test, must be available for all other tests too 
*
* application-test.properties with h2 properties created and added to all tests
* data.sql (default sql script) added and will be executed by all tests.
* data-custom.sql script added and will be executed by one test.

#### 3. customerRepositoryDemo excluded
* customerRepositoryDemo bean, which insert in the table on start up, excluded. At least for a while.
* I want simpler situation when postgres database is used for run and h2 database for tests.

#### 4. Appendix, SQL queries against postgres db:
```
INSERT INTO customer (id,first_name,last_name)  VALUES (1, 'first', 'last');
SELECT COUNT(*) from customer;
```


## Version 0.0.9
### update
* replace h2 with postgres
  1. configuration properties for postgres 
  2. dependency for postgres 
* [multi line command](https://stackoverflow.com/questions/55847359/multi-line-docker-run-command-on-windows)
* [postgres example](https://www.javaguides.net/2019/08/spring-boot-spring-data-jpa-postgresql-example.html)
* postgres docker
* one line command:
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0     --name ivan -e POSTGRES_USER=ivan     -e POSTGRES_PASSWORD=ivan -e POSTGRES_DB=ivan     -p 5432:5432 postgres:13.1
```
* multi line command:
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0  ^
  --name ivan -e POSTGRES_USER=ivan -e POSTGRES_PASSWORD=ivan -e POSTGRES_DB=ivan  ^
  -p 5432:5432 postgres:13.1
```

## Version 0.0.8
### update
* CHANGELOG_DETAILS folder added, in order to have more files attached to single change.


## Version 0.0.7
### new
* Accessing Data with JPA (h2)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
 

## Version 0.0.6
### new
* REST API parameters according to Ivan Basic
  1. path
  2. query
  3. header
  4. body
* [rest api parameters](https://idratherbewriting.com/learnapidoc/docapis_doc_parameters.html)
* [uri naming conventions](https://restfulapi.net/resource-naming/)
* [just annoying](https://stackoverflow.com/questions/54205486/how-to-avoid-sharing-is-only-supported-for-boot-loader-classes-because-bootstra )
```
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
```


## Version 0.0.5
### update
* unit test (MockMvc) analyzed a bit
* GreetingControllerAnalyzedAgainTest, GreetingControllerTest 


## Version 0.0.4
### update 
* integration test analyzed a bit
* GreetingControllerITest, GreetingControllerAnalyzedAgainITest


## Version 0.0.3
### new
* [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot)
* First unit test for controller
* First full-stack integration test for controller
* Spring boot `bean-list`. The list is much bigger (155) than in the guide (34). See CH 


## Version 0.0.2
### new
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service)


## Version 0.0.1 
### update
* test error fixed 
* [stackoverflow](https://stackoverflow.com/questions/77951485/getting-a-java-agent-has-been-loaded-warning-in-intellij-after-upgrading-jdk-17)
```
WARNING: A Java agent has been loaded dynamically (C:\Users\ivan\.m2\repository\net\bytebuddy\byte-buddy-agent\1.14.19\byte-buddy-agent-1.14.19.jar)
WARNING: If a serviceability tool is in use, please run with -XX:+EnableDynamicAgentLoading to hide this warning
WARNING: If a serviceability tool is not in use, please run with -Djdk.instrument.traceUsage for more information
WARNING: Dynamic loading of agents will be disallowed by default in a future release
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
```


## Version 0.0.1  2024-08-23
### new 
* [quickstart](https://spring.io/quickstart)
* pom.xml 
* main for simple Spring Boot application
* readme
* changelog
* gitignore,  how to remove file or folder but only for git(hub)
  1. save them somewhere else and remove them
  2. add them to .gitignore
  3. bring them back