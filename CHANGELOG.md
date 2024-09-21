# Changelog

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