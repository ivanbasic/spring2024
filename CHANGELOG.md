# Changelog

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