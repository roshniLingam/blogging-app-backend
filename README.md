# Blogging Application
This repository contains the backend code for the blogging application using Spring Boot 3.

**Default host:** http://localhost:8080

**Swagger API Documentation:**  http://localhost:8080/swagger-ui/index.html . This link will open only when application is running.

## Requirements
To build and run the application locally, you need:
- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
- [Lombok](https://projectlombok.org/download)
- [MySQL Database](https://dev.mysql.com/downloads/installer/)

## Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.bloggingapp.bloggingapp.BloggingappApplication` class in `src\main` from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Features
### Bean Validation

Include the following in `pom.xml`
```xml
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```
### Spring Security v6.1

Include the following in `pom.xml`
```xml
<!--Spring Security-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
### JWT Authorisation

Include the following in `pom.xml`
```xml
<!-- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt -->
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.12.3</version>
</dependency>
```
### Swagger 3

Include the following dependencies in `pom.xml`. Note that version of each dependencies must be compatible with each other to avoid build failure.
```xml
<!-- https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui -->
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	<version>2.2.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.swagger.core.v3/swagger-annotations -->
<dependency>
	<groupId>io.swagger.core.v3</groupId>
	<artifactId>swagger-annotations</artifactId>
	<version>2.2.0</version>
</dependency>

<!-- https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-boot-starter</artifactId>
	<version>3.0.0</version>
</dependency>
```