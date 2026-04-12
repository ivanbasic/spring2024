# Changelog

## v57 CHANGELOG UPDATED
### Modified
* CHANGELOG.md 

## v56 ssia-ch12 FILTERING AT THE METHOD LEVEL
### Added
* spring security, filtering by method, simpler than employee_id approach
* db2.employees linked with db3.user via new db2.employees.username
* /db2/employees/my-team, business rules:
  * ADMIN users see all employees
  * Employees see colleagues in their department
  * Non-employees see nothing
### Modified
* Employee entity - added username field and getters
* EmployeeRepo - added findByUsername() and findByDepartmentDepartmentId()
* EmployeeService - added getMyTeam() method with simplified logic
* EmployeeController - added endpoint for team visibility
### Notes
* No custom security classes needed (unlike lesson 055)
* Simpler implementation: db2.employees.username instead of db3.user.employee_id
* See CHANGELOG_DETAILS/0.0.56a_Username_Based_Filtering.md


## v55 ssia-ch12 FILTERING AT THE METHOD LEVEL (NOT MERGED)
### Added
* spring security, filtering by method
* db2.employees linked with db3.user via new db3.user.employee_id
* /db2/employees/my-team, business rules:
  * ADMIN users see all employees
  * MANAGER users see only employees in their department
  * Users without employee_id see nothing
* Custom UserDetails implementation (EmployeeLinkedUserDetails) with employee_id field
* Custom JdbcUserDetailsManager (EmployeeLinkedUserDetailsManager) that loads employee_id
* New manager users: ada & karl with ROLE_MANAGER
* SQL-based filtering in EmployeeService for role-based data access
### Modified
* Employee and Department entities - added getters
* EmployeeRepo - added findByDepartment_DepartmentId() method
* SecurityConfig - uses EmployeeLinkedUserDetailsManager
### Notes
* Demonstrates proper extension of JdbcUserDetailsManager
* Shows SQL-based filtering (better performance than @PostFilter)
* See CHANGELOG_DETAILS/0.0.55a_EmployeeId_Based_Filtering.md


## v54 CLAUDE DESKTOP SETUP
### Added
* Claude Desktop setup guide for Windows with MCP file sharing
* Documentation on installing and configuring Windows-MCP extension
* Troubleshooting steps for common MCP connection issues
### Notes
* This version focuses on tooling and development environment setup
* No Java code changes in this version
* See CHANGELOG_DETAILS/0.0.54a_Claude_desktop_Windows.md for detailed setup instructions

## v53 ssia-ch7 AUTHORITIES AND ROLES IN DATABASE
### Added
* Authorization support using authorities and roles stored in DB3
* New /admin/** endpoints protected by ADMIN authority
* New /joker/** endpoint demonstrating method-level authorization
* User ivan with authorities: 
  * read, ADMIN, ROLE_JOKER
* JWT authorization flow has by default SCOPE_ prefix:
  * SCOPE_ADMIN, SCOPE_ROLE_JOKER, SCOPE_read
### Updated
* Security configuration updated accordingly
### Notes
* hasAuthority() works directly with JWT scopes and is the most flexible option
* hasRole() is a specialization that expects a ROLE_ prefix
* @EnableMethodSecurity not required in this setup
* Authorization is demonstrated at two levels:
  * Request matcher level (SecurityFilterChain, /admin/**)
  * Method level (@PreAuthorize, /joker/**)

  
## v52 ssia-ch5 CUSTOM EARLY-EXIT FILTER
### Added
* Introduced new filter as an early-exit security filter
### Updated
* security configuration updated accordingly
### Note
* filter behavior:
```
if (everythingIsOk) {
    filterChain.doFilter(request, response); // continue
} else {
   response.sendError(400);
   return; // stop chain
}
```




## v51 ssia-ch6 AUTHENTICATION ENTRY POINTS
### Added
* Custom AuthenticationEntryPoint implementations for:
  * HTTP Basic authentication failures
  * JWT authentication failures
  * Global authentication fallback
### Updated
* security configuration updated accordingly 
### Note 
* AuthenticationEntryPoint - customizes the HTTP response when authentication fails (status code, headers, body)
* Triggered by ExceptionTranslationFilter - the bridge between Java exceptions and HTTP responses
* Filters are part of the security filter chain and are usually instantiated directly, while...  
* AuthenticationEntryPoints are reusable strategy components and are best managed as Spring beans.
* authenticationEntryPoint() appears 3 times in SecurityConfig - each on a different configurer class:
  * httpBasic(basic -> basic.authenticationEntryPoint())        - HttpBasicConfigurer
  * oauth2ResourceServer(oauth2 -> oauth2.authenticationEntryPoint()) - OAuth2ResourceServerConfigurer
  * exceptionHandling(ex -> ex.authenticationEntryPoint())     - ExceptionHandlingConfigurer
* same method name, but no shared interface - just reused independently


## v50 ssia-ch6 AUTHENTICATION PROVIDER
### Summary
#### AuthenticationProvider
#### the graph 
```
AuthenticationManager (ProviderManager)
├── DaoAuthenticationProvider (implements AuthenticationProvider)
│    ├── UserDetailsService (JdbcUserDetailsManager)
│    └── PasswordEncoder
└── JwtAuthenticationProvider (implements AuthenticationProvider)
└── JwtDecoder
```
#### description
AuthenticationManager can have multiple AuthenticationProviders because it acts as a dispatcher that tries each provider in order and delegates authentication to the first one that supports the given Authentication type (Basic credentials vs JWT token)
#### note
Spring's default providers (DaoAuthenticationProvider + JwtAuthenticationProvider) already handle both auth types - no custom AuthenticationProvider needed


## v49 ssia-ch5 CUSTOM SECURITY FILTERS
### Added
* Custom security filters demonstrating insertion before, after, and at a specific position in the Spring Security filter chain
* DEBUG logging for Spring Security filters
### Updated
* Security configuration updated to register and order custom filters


## v48 LOGGING CONFIGURATION
### Added
* INFO logs (configuration and controllers) 
### Updated
* log configuration

 
## v47 ssia-ch4 DELEGATING PASSWORD ENCODER
### Added
* Support for multiple password encoders using `DelegatingPasswordEncoder`
* Users authenticated with `{bcrypt}`, `{argon2}`, `{pbkdf2}`, `{sha256}`, `{ldap}`, and `{noop}`
* Integration test validating authentication for all supported encoders
* Bouncy Castle cryptography libraries (bcprov, bcpkix) to support Argon2.
### Updated
* Database seed data extended with users using different password encoders
* Authentication tests refactored to verify encoder compatibility
### Summary
* Demonstrates how Spring Security selects a `PasswordEncoder` based on password prefix
* Confirms that legacy and modern encoders can coexist safely


## v46 ssia-ch4 BCRYPT PASSWORD ENCODER
### Added
* BCrypt password support for JDBC-backed users
* New DB3 user (`bcryptuser`) with `{bcrypt}`-encoded password
* Unit test demonstrating BCrypt behavior (random salt, non-repeatable hashes, `matches()` verification)
* Documentation for password hashing workflow
* updated postman collection version 46 
### Updated
* DB3 initialization scripts to include BCrypt-based user
* Security documentation to explain password encoding and verification flow


## v45 ssia-ch3 JDBC USER DETAILS MANAGER
### Added
* DB-backed user management using DB3 (`JdbcUserDetailsManager`)
* DB3 user schema/data initialization scripts (`schema-db3.sql`, `data-db3.sql`)
### Updated
* Security configuration switched from in-memory users to DB3-backed users
* Adjusted security test for HomeController
### Removed
* Removed commented legacy security beans (`mySecurityFilterChain`, in-memory user bean)
* Removed unused qualifiers from DB2 configuration
### Summary
* InMemoryUserDetailsManager is replaced with...
* JdbcUserDetailsManager 
* It works because both implement UserDetailsService 


## v44 sp-boot THIRD DATASOURCE DB3 CONFIGURATION
### Added
* Added third datasource (DB3) with full JPA configuration
* Introduced `Db3AutoConfiguration` mirroring DB1 and DB2 setup
* Extended `application.yml` with `spring.datasource.db3` configuration
* Added MySQL JDBC driver (`mysql-connector-j`) to `pom.xml`


## v43 sp-boot MYSQL DB3 DOCKER
### Added
* Added third MySQL database (DB3) as a standalone Docker container
* Extended `startup-DB.bat` with DB3 startup command
* Added manual DB3 verification via MySQL command-line client


## v42 sp-boot UPGRADE 3.3.6 TO 3.5.4
### Updated
* Spring Boot upgraded from 3.3.6 to 3.5.4
* Security configuration migrated from `authorizeRequests` to `authorizeHttpRequests` (Spring Security 6.3+)
* Disabled `spring.jpa.open-in-view` to close the JPA EntityManager after the service layer
* Simplified header check in `GreetingController_AnalyzedAgain_ITest` to avoid brittle header count assertions
### Removed
* Custom `maven-surefire-plugin` configuration (now using Spring Boot's managed version)


## v41 sp-boot ACTUATOR PART 4
### Updated
* Spring Boot Actuator part 4:
  * health group(s)
  * authorization investigation and tests
### Resources
  * see note in CHANGELOG_DETAILS/0.0.41_a_actuator_notes.md

## v40 ssia-ch2 CONFIGURING USER DETAILS (NOT MERGED)
### Not merged in main
### New
* Spring Boot Security: user, Configuring in different ways
### Resources
* Spring security in action: 2.3.3 Configuring in different ways
  * [ssia-ch2-ex3](https://github.com/ivanbasic/spring-security-in-action-2nd-Ed/blob/master/ssia-ch11-ex3/src/main/java/com/laurentiuspilca/ssia/config/ProjectConfig.java)
### Notes:
Location of UserDetails could be:
* Local to SecurityFilterChain. The problems:
  * No Spring bean — so you can't inject or autowire it elsewhere.
  * No access from tests to inspect or override the users.
  * Harder to debug — can't retrieve it from ApplicationContext.
* Defined as a separate Spring bean: The benefits:
  * You can inject it anywhere with @Autowired
  * In tests: var uds = context.getBean(UserDetailsService.class);
  * In logs, debug tools, and even actuator endpoints — it's visible.
### Final Thought about Notes:
* When it is a bean, it seems easier to get info about users.
* Because then Spring manages it, and you get all the goodies of injection, testing, and observability.
* The inline SecurityFilterChain version is great for demos or self-contained examples...
* but for real-world work, making UserDetailsService a bean is better practice.

## v39 sp-boot ACTUATOR PART 3
### Updated
* Spring Boot Actuator part 3: Tests
  * ActuatorResponseTest: tests content + behavior
  * ActuatorSecurityTest: confirms access rules and protection 

## v38 sp-boot ACTUATOR PART 2
### Updated
* Spring Boot Actuator part 2
  * a) list of beans
  * b) metrics: requests statistics


## v37 sp-boot ACTUATOR PART 1
### New
* Spring Boot Actuator
  * Added Spring Boot Actuator with /manage base path
  * health, info:  public
  * metrics: need authorization


## v36 CHANGELOG UPDATE
### Updated
* Update this file for lessons 0.0.1 until now


## v35 CHANGELOG UPDATE
### Updated 
* Update this changelog related to lessons 0.0.30 and 0.0.29


## v34 sp-sec AUTOMATED TESTING
### New
* Spring Boot Security: Automated Testing.
### Resources
* [Dan Vega - JWT](https://www.danvega.dev/blog/spring-security-jwt)
* [@SpringBootTest vs @WebMvcTest](https://stackoverflow.com/questions/39865596/difference-between-using-mockmvc-with-springboottest-and-using-webmvctest)
* [Spring Boot Testing Documentation](https://docs.spring.io/spring-boot/reference/testing/spring-boot-applications.html#testing.spring-boot-applications)
### TODO
* [restrict basic auth only to /token](https://github.com/danvega/jwt/blob/master/src/main/java/dev/danvega/jwt/config/SecurityConfig.java#L69)


## v33 sp-sec JWT LAMBDA CONFIGURATION
### Updated
* Removed deprecated JWT filter and replaced it with the recommended   Lambda configuration  
### Resources
* [Spring Security deprecated issue with JWT](https://stackoverflow.com/questions/76339307/spring-security-deprecated-issue)
* [Spring Security Docs](https://docs.spring.io/spring-security/reference/migration-7/configuration.html#_use_the_lambda_dsl)


## v32 sp-sec DOCUMENTATION
### Updated
* Updated documentation for Spring Boot Security.


## v31 sp-sec JWT WITH RSA KEYS
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


## v30 sp-sec INTELLIJ NAVIGATION
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

## v29 ssia-ch2 BASIC AUTH AND POSTMAN
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


## v28 sp-sec TESTING FIXES
### New
* Fixed tests when spring security is enabled
### Solutions
* 1 @MockBean(SecurityFilterChain.class)
* 2 @WithMockUser
* 3 @AutoConfigureMockMvc(addFilters=false)
### Resources
* General search: `test @autoconfiguremockmvc 401 unauthorized`
* [StackOverflow: 401 Unauthorized in JUnit Test](https://stackoverflow.com/questions/78358519/401-unauthorized-junit-test)


## v27 ssia-ch2 ENABLE SPRING SECURITY
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

## v26 ENABLE HTTPS
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


## v25 TRANSACTIONAL TESTS
### New
* Added support for transactional tests for both primary and secondary data sources.
  * `@Transactional` used for `db1` (default datasource).
  * `@Transactional("DB2")` configured for `db2`.
### Resources
* [pg sequence 1](https://webkul.com/blog/fix-missing-sequence-table-postgresql/)
* [pg sequence 2](https://stackoverflow.com/questions/60687826/the-increment-size-of-the-sequence-is-set-to-50-in-the-entity-mapping-while-th)


## v23 DATABASE REFACTORING
### Updated
* refactoring database structure (model and repo folders)
* documentation, clean up

## v22 README AND CLEANUP
### New
* `README.md` with project overview and setup instructions.
### Updated
* clean up


## v21 sp-jpa REPOSITORY RETURN TYPES
### Updated
* Extended `EmployeeRepo` with various return types:
  * `int` for count queries (e.g., `countXYZ`),
  * entity objects for standard `findXYZ` methods,
  * DTOs/records for custom projections and queries.
* Added repository tests to verify each query type.

### Resources
* [Baeldung – JPA with Java Records](https://www.baeldung.com/spring-jpa-java-records)


## v20 sp-jpa REPOSITORIES RENAME
### Updated
* Repositories. Names of entities changed


## v19 sp-jpa REPOSITORIES
### New
* Repositories. Derived Query, JPQL, and Native for EmployeeRepo.
### Resources
* [1 baeldung Derived Query Methods](https://www.baeldung.com/spring-data-derived-queries)
* [2 baeldung JPQL and Native](https://www.baeldung.com/spring-data-jpa-query)
* [3 spring.io query keywords](https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html)
* [4 oracle HR schema](https://download.oracle.com/oll/tutorials/DBXETutorial/html/module2/les02_load_data_sql.htm)
* [5 error Not a Managed Type](https://www.baeldung.com/spring-data-jpa-not-managed-type-exception)


## v18 sp-jpa SCHEMA SQL INIT
### New
* Execute schema-db2.sql for database db2 on startup
  * [using DataSourceInitializer](https://stackoverflow.com/questions/39280340/how-to-run-sql-scripts-and-get-data-on-application-startup)
* controller test for db2

 
## v17 sp-boot MULTIPLE DATASOURCES
### Updated
* multiple datasource's. databases db1 and db2
* db2 works, db2 controller/service/repo/table works
* old tests work, but only when postgres db2 is up
### Fixes:
* Resolved test failures by correcting the `application-test.yml` configuration for `db2`.
  * Issue: same name used for two different H2 databases.
### TODO:
* Automate execution of `schema-db2.sql` on application startup.


## v16.1 sp-boot DATASOURCE FIX
### Updated
* all tests are actually not working. somehow, h2 is replaced with postgres. hm?
* fixed. just wrong setup in application-test.yml for db1
  * db1 and jdbcUrl instead of url


## v16 sp-boot MULTIPLE DATASOURCE SETUP
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


## v15 sp-boot SCHEMA SQL INIT
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

  
## v14 sp-boot YAML PROPERTIES
### Updated
* Using yaml format for the properties
* [yml for the tests](https://stackoverflow.com/questions/21271468/spring-propertysource-using-yaml)
  * @TestPropertySource doesn't work without password and username 
* log sql commands 
* clean up


## v11 sp-boot BEAN READER
### Updated
* BeanReader and RepoDemo removed from spring main class
* BeanReader separate 
  * existing spring boot beans 
  * newly created
* clean up  


## v10 sp-boot CONTROLLER SERVICE REPO
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


## v9 sp-boot POSTGRES DATABASE
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


## v8 CHANGELOG DETAILS FOLDER
### Updated
* CHANGELOG_DETAILS folder added, in order to have more files attached to single change.


## v7 sp-jpa INTRO
### New
* First integration of Spring Data JPA using an in-memory H2 database.
* Followed the official Spring guide to set up a basic entity, repository, and data access flow.
### Resources
* [Spring Guide: Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)


## v6 sp-boot REST API PARAMETERS
### New
* REST API parameters (according to Ivan Basic):
  1. path
  2. query
  3. header
  4. body

### Resources
* [REST API Parameters – I'd Rather Be Writing](https://idratherbewriting.com/learnapidoc/docapis_doc_parameters.html)
* [URI Naming Conventions – restfulapi.net](https://restfulapi.net/resource-naming/)
### Misc Notes
* JVM warning about boot loader classes – annoying but harmless:  
  [StackOverflow](https://stackoverflow.com/questions/54205486/how-to-avoid-sharing-is-only-supported-for-boot-loader-classes-because-bootstra)
```
Java HotSpot(TM) 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been appended
```


## v5 sp-boot UNIT TESTS
### Updated
* unit test (MockMvc) analyzed a bit
* GreetingControllerAnalyzedAgainTest, GreetingControllerTest 


## v4 sp-boot INTEGRATION TESTS
### Updated 
* integration test analyzed a bit
* GreetingControllerITest, GreetingControllerAnalyzedAgainITest


## v3 sp-boot FIRST TESTS
### New
* [Building an Application with Spring Boot](https://spring.io/guides/gs/spring-boot)
* First unit test for controller
* First full-stack integration test for controller
* Spring boot `bean-list`. The list is much bigger (155) than in the guide (34). See CH folder 


## v2 sp-boot RESTFUL SERVICE
### New
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service)


## v1.1 sp-boot TEST ERROR FIX
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


## v1 sp-boot FIRST APPLICATION
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
