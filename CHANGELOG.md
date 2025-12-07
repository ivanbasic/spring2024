# Changelog

## Version 0.0.44
### Added
* Added third datasource (DB3) with full JPA configuration
* Introduced `Db3AutoConfiguration` mirroring DB1 and DB2 setup
* Extended `application.yml` with `spring.datasource.db3` configuration
* Added MySQL JDBC driver (`mysql-connector-j`) to `pom.xml`


## Version 0.0.43
### Added
* Added third MySQL database (DB3) as a standalone Docker container
* Extended `startup-DB.bat` with DB3 startup command
* Added manual DB3 verification via MySQL command-line client


## Version 0.0.42
### Updated
* Spring Boot upgraded from 3.3.6 to 3.5.4
* Security configuration migrated from `authorizeRequests` to `authorizeHttpRequests` (Spring Security 6.3+)
* Disabled `spring.jpa.open-in-view` to close the JPA EntityManager after the service layer
* Simplified header check in `GreetingController_AnalyzedAgain_ITest` to avoid brittle header count assertions
### Removed
* Custom `maven-surefire-plugin` configuration (now using Spring Boot's managed version)


## Version 0.0.41
### Updated
* Spring Boot Actuator part 4:
  * health group(s)
  * authorization investigation and tests
### Resources
  * see note in CHANGELOG_DETAILS/0.0.41_a_actuator_notes.md

## Version 0.0.40
### Not merged in main
### New
* Spring Boot Security: user, Configuring in different ways
### Resources
* Spring security in action: 2.3.3 Configuring in different ways
  * [ssia-ch2-ex3](https://github.com/ivanbasic/spring-security-in-action-2nd-Ed/blob/master/ssia-ch11-ex3/src/main/java/com/laurentiuspilca/ssia/config/ProjectConfig.java)
### Notes:
Location of UserDetails could be:
* Local to SecurityFilterChain. The problems:
  * No Spring bean — so you can’t inject or autowire it elsewhere.
  * No access from tests to inspect or override the users.
  * Harder to debug — can’t retrieve it from ApplicationContext.
* Defined as a separate Spring bean: The benefits:
  * You can inject it anywhere with @Autowired
  * In tests: var uds = context.getBean(UserDetailsService.class);
  * In logs, debug tools, and even actuator endpoints — it's visible.
### Final Thought about Notes:
* When it is a bean, it seems easier to get info about users.
* Because then Spring manages it, and you get all the goodies of injection, testing, and observability.
* The inline SecurityFilterChain version is great for demos or self-contained examples...
* but for real-world work, making UserDetailsService a bean is better practice.

## Version 0.0.39
### Updated
* Spring Boot Actuator part 3: Tests
  * ActuatorResponseTest: tests content + behavior
  * ActuatorSecurityTest: confirms access rules and protection 

## Version 0.0.38
### Updated
* Spring Boot Actuator part 2
  * a) list of beans
  * b) metrics: requests statistics


## Version 0.0.37
### New
* Spring Boot Actuator
  * Added Spring Boot Actuator with /manage base path
  * health, info:  public
  * metrics: need authorization


## Version 0.0.36
### Updated
* Update this file for lessons 0.0.1 until now


## Version 0.0.35
### Updated 
* Update this changelog related to lessons 0.0.30 and 0.0.29


## Version 0.0.34
### New
* Spring Boot Security: Automated Testing.
### Resources
* [Dan Vega - JWT](https://www.danvega.dev/blog/spring-security-jwt)
* [@SpringBootTest vs @WebMvcTest](https://stackoverflow.com/questions/39865596/difference-between-using-mockmvc-with-springboottest-and-using-webmvctest)
* [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications)
### TODO
* [restrict basic auth only to /token](https://github.com/danvega/jwt/blob/master/src/main/java/dev/danvega/jwt/config/SecurityConfig.java#L69)


## Version 0.0.33
### Updated
* Removed deprecated JWT filter and replaced it with the recommended   Lambda configuration  
### Resources
* [Spring Security deprecated issue with JWT](https://stackoverflow.com/questions/76339307/spring-security-deprecated-issue)
* [Spring Security Docs](https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl)


## Version 0.0.32
### Updated
* Updated documentation for Spring Boot Security.


## Version 0.0.31
### New
* Implemented JWT Authentication with RSA Keys

### Source code steps:
* 0 pom.xml: `spring-boot-starter-oauth2-resource-server` & `spring-boot-configuration-processor`
* 1 SecurityConfig class:
  * SecurityFilterChain, jwt added
  * InMemoryUserDetailsManager bean. user ivan
  * JWT decoder and encoder bean
* 2 RSA Public & Private Keys
* 3 Token service and controller

### Postman Steps:
* 0 call new /token request using basic auth, using user which is set in InMemoryUserDetailsManager bean
* 1 Copy token
* 2 root of the collection should have authorization `Bearer Token` (jwt token). Past token.
* 3 all other requests should inherit auth  from parent/root

### Resources
* [Dan Vega JWT](https://www.danvega.dev/blog/spring-security-jwt)
* [OpenSSL for Windows](https://stackoverflow.com/questions/50625283/how-to-install-openssl-in-windows-10)


## Version 0.0.30
### New
* Explored IntelliJ navigation techniques for locating Spring bean declarations `SecurityFilterChain`
  * SecurityFilterChain 
  * @Bean DefaultSecurityFilterChain defaultSecurityFilterChain in...
  * SpringBootWebSecurityConfiguration
### Resources 
* [Amigoscode - Spring Security Tutorial](https://youtu.be/b9O9NI-RJ3o?t=908)
### IntelliJ navigation methods (best practices):
* Quick navigation: `Ctrl+Shift+N` → type `SecurityFilterChain`.
* Direct class inspection: `SecurityFilterChainConfiguration` in `SpringBootWebSecurityConfiguration`.

## Version 0.0.29 
### New
* Basic Authentication and Postman
### Postman Configuration:
* In the root of postman collection, set Authorization  to `Basic`
  * User should be `user`
  * Password should be copied from `generated security password` from app log
* For all endpoints, set Authorization `inherit auth from parent`

### Troubleshooting
* Endpoint returns `302` in browser, but `401` in Postman.
  * https://github.com/spring-projects/spring-boot/issues/30155
    * You have to set the header `Accept` to `text/html` instead of `*/*`


## Version 0.0.28 
### New
* Fixed tests when spring security is enabled
### Solutions
* 1 @MockBean(SecurityFilterChain.class)
* 2 @WithMockUser
* 3 @AutoConfigureMockMvc(addFilters=false)
### Resources
* General search: `test @autoconfiguremockmvc 401 unauthorized`
* [StackOverflow: 401 Unauthorized in JUnit Test](https://stackoverflow.com/questions/78358519/401-unauthorized-junit-test)


## Version 0.0.27 
### new
* Enabled Spring Security in the application for the first time.
### Steps:
* 0 dependency `spring-boot-starter-security` added in pom.xml
* 1 no single change in the source code
* 2 all endpoints secured with error `401 Unauthorized`
* 3 `/login` and `/logout` endpoints added when using browser
* 4 `Basic auth` with user `user` and password ...
* 5 `Using generated security password: ...` from app startup log
### Resources
* [Spring Security Architecture Overview](https://docs.spring.io/spring-security/reference/servlet/architecture.html)

## Version 0.0.26 
### New
* HTTPS support enabled for the application using a self-signed certificate.
### Steps:
* 0 Public-key cryptography and Keystore tool. See `0.0.26a_Keystore_Tutorial.md`
* 1 Enable HTTPS in server app 
* 2 Consume it using client app and rest template
* 3 TestRestTemplate tests were down after step 1, fixed again after step 2


### Resources, most of them not perfect
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
  * ⚠️ Warning: misleading advice in top answers. Baeldung trap for 2K+ developers 

* [this step is missing: how to get .crt from browser](TODO)

* [certificate error](https://stackoverflow.com/questions/3093112/certificateexception-no-name-matching-ssl-someurl-de-found)
  * very useful, localhost trick:
  * When you generate certificate, on question "What is your first and last name?" answer : localhost


## Version 0.0.25
### New
* Added support for transactional tests for both primary and secondary data sources.
  * `@Transactional` used for `db1` (default datasource).
  * `@Transactional("DB2")` configured for `db2`.
### Resources
* [pg sequence 1](https://webkul.com/blog/fix-missing-sequence-table-postgresql/)
* [pg sequence 2](https://stackoverflow.com/questions/60687826/the-increment-size-of-the-sequence-is-set-to-50-in-the-entity-mapping-while-th)


## Version 0.0.23
### Updated
* refactoring database structure (model and repo folders)
* documentation, clean up

## Version 0.0.22
### New
* `README.md` with project overview and setup instructions.
### Updated
* clean up


## Version 0.0.21
### Updated
* Extended `EmployeeRepo` with various return types:
  * `int` for count queries (e.g., `countXYZ`),
  * entity objects for standard `findXYZ` methods,
  * DTOs/records for custom projections and queries.
* Added repository tests to verify each query type.

### Resources
* [Baeldung – JPA with Java Records](https://www.baeldung.com/spring-jpa-java-records)


## Version 0.0.20
### Updated
* Repositories. Names of entities changed


## Version 0.0.19
### New
* Repositories. Derived Query, JPQL, and Native for EmployeeRepo.
### Resources
* [1 baeldung Derived Query Methods](https://www.baeldung.com/spring-data-derived-queries)
* [2 baeldung JPQL and Native](https://www.baeldung.com/spring-data-jpa-query)
* [3 spring.io query keywords](https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html)
* [4 oracle HR schema](https://download.oracle.com/oll/tutorials/DBXETutorial/html/module2/les02_load_data_sql.htm)
* [5 error Not a Managed Type](https://www.baeldung.com/spring-data-jpa-not-managed-type-exception)


## Version 0.0.18
### New
* Execute schema-db2.sql for database db2 on startup
  * [using DataSourceInitializer](https://stackoverflow.com/questions/39280340/how-to-run-sql-scripts-and-get-data-on-application-startup)
* controller test for db2

 
## Version 0.0.17
### Updated
* multiple datasource's. databases db1 and db2
* db2 works, db2 controller/service/repo/table works
* old tests work, but only when postgres db2 is up
### Fixes:
* Resolved test failures by correcting the `application-test.yml` configuration for `db2`.
  * Issue: same name used for two different H2 databases.
### TODO:
* Automate execution of `schema-db2.sql` on application startup.


## Version 0.0.16.1
### Updated
* all tests are actually not working. somehow, h2 is replaced with postgres. hm?
* fixed. just wrong setup in application-test.yml for db1
  * db1 and jdbcUrl instead of url


## Version 0.0.16
### New
* Introduced multiple datasource support — initial setup includes `db1`.
* Verified all tests still pass with the updated configuration.

### Notes:
* Early research into multi-database setup — most tutorials were either outdated or unhelpful.
* Identified good naming practices in [AshokIT multi-db tutorial](https://www.youtube.com/watch?v=mIFIb_JE47U&ab_channel=AshokIT).

### Common Issues & References:
* Dialect error when DB is inaccessible – [StackOverflow](https://stackoverflow.com/questions/78036592/why-cant-spring-boot-deduce-hibernate-dialect)
* `url` vs `jdbcUrl` confusion in config – [StackOverflow](https://stackoverflow.com/questions/49088847/after-spring-boot-2-0-migration-jdbcurl-is-required-with-driverclassname)

### Prepared for Future Expansion:
* PostgreSQL containers for `db2` and `db3` created and ready:
  ```bash
  docker run --name db2 -e POSTGRES_USER=db2 -e POSTGRES_PASSWORD=db2 -e POSTGRES_DB=db2 -e PGPORT=5002 -p 5002:5002 postgres:13.1
  docker run --name db3 -e POSTGRES_USER=db3 -e POSTGRES_PASSWORD=db3 -e POSTGRES_DB=db3 -e PGPORT=5003 -p 5003:5003 postgres:13.1


## Version 0.0.15
### New & Update
#### MAIN section update
* on startup, create schema schema1 in postgres database (call schema.sql) 
  * [just plain wrong: make sure to disable... ddl-auto if you use schema.sql](https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/howto-database-initialization.html)
  * [right one: As of Spring Boot Version 2.7 ... spring.sql.init.mode ](https://stackoverflow.com/questions/49438517/why-spring-boot-2-0-application-does-not-run-schema-sql)
* docker command for postgres simplified
  ```
  docker run --name ivan -e POSTGRES_USER=ivan -e POSTGRES_PASSWORD=ivan -e POSTGRES_DB=ivan -p 5432:5432 postgres:13.1
  ```
#### TEST section update
* on startup, create schema schema1 in H2 database
  * test will call schema.sql from main resources
* TEST philosophy changed and looks a little bit complicated
  * application-test.yml with h2 properties is still used
  * schema.sql from main resources will be called
  * schema.sql from test resources will be called
  * data.sql from test resources will be called 
  * data-custom.sql script for one test is now part of transaction (it will be rolled back after test)

  
## Version 0.0.14
### Updated
* Using yaml format for the properties
* [yml for the tests](https://stackoverflow.com/questions/21271468/spring-propertysource-using-yaml)
  * @TestPropertySource doesn't work without password and username 
* log sql commands 
* clean up


## Version 0.0.11
### Updated
* BeanReader and RepoDemo removed from spring main class
* BeanReader separate 
  * existing spring boot beans 
  * newly created
* clean up  


## Version 0.0.10
### New
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
### Updated
* Replace H2 with postgres
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
### Updated
* CHANGELOG_DETAILS folder added, in order to have more files attached to single change.


## Version 0.0.7
### New
* First integration of Spring Data JPA using an in-memory H2 database.
* Followed the official Spring guide to set up a basic entity, repository, and data access flow.
### Resources
* [Spring Guide: Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


## Version 0.0.6
### New
* REST API parameters (according to Ivan Basic):
  1. path
  2. query
  3. header
  4. body

### Resources
* [REST API Parameters – I’d Rather Be Writing](https://idratherbewriting.com/learnapidoc/docapis_doc_parameters.html)
* [URI Naming Conventions – restfulapi.net](https://restfulapi.net/resource-naming/)
### Misc Notes
* JVM warning about boot loader classes – annoying but harmless:  
  [StackOverflow](https://stackoverflow.com/questions/54205486/how-to-avoid-sharing-is-only-supported-for-boot-loader-classes-because-bootstra)
```
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
```


## Version 0.0.5
### Updated
* unit test (MockMvc) analyzed a bit
* GreetingControllerAnalyzedAgainTest, GreetingControllerTest 


## Version 0.0.4
### Updated 
* integration test analyzed a bit
* GreetingControllerITest, GreetingControllerAnalyzedAgainITest


## Version 0.0.3
### New
* [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot)
* First unit test for controller
* First full-stack integration test for controller
* Spring boot `bean-list`. The list is much bigger (155) than in the guide (34). See CH folder 


## Version 0.0.2
### New
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service)


## Version 0.0.1 
### Updated
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
### New 
* [quickstart](https://spring.io/quickstart)
* pom.xml 
* main for simple Spring Boot application
* readme
* changelog
* gitignore,  how to remove file or folder but only for git(hub)
  1. save them somewhere else and remove them
  2. add them to .gitignore
  3. bring them back
