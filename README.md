# Dummy Spring Boot Blog Backend

## Project Overview

**Dummy Spring Boot Blog Backend** is an experimental project designed to serve as a testing ground for various technologies and best practices. It provides an opportunity to apply knowledge gained from practical experiences and to explore cutting-edge technologies within a real-world project context. Focused on creating a simple blog backend, this project showcases skills and fosters a dynamic learning environment.

## Key Features

- **Programming Language:** Java 17, with records and pattern matching.
- **Java Framework:** Spring Boot 3.
- **Data Management:** Spring Data JPA.
- **Database:** PostgreSQL for reliable SQL data storage.
- **Security:** Spring Security with Keycloak.
- **API Documentation:** Swagger documentation via springdoc-openapi-ui.
- **Integration Testing:** Comprehensive testing with RestTemplate.
- **Dockerized Testing:** Utilizing TestContainers for local PostgreSQL container deployment.
- **Testing Libraries:** AssertJ and Mockito for rigorous testing.

## Current Technology Stack

- **Programming Language:** Java 17.
- **Framework:** Spring Boot 3.1.2.
- **Data Access:** Spring Data JPA.
- **Database:** PostgreSQL.
- **Security:** Spring Security with Keycloak.
- **Documentation:** Swagger with springdoc-openapi-ui.
- **Integration Testing:** RestTemplate.
- **Docker:** TestContainers for local PostgreSQL image.
- **Testing Libraries:** AssertJ and Mockito.

## Project Goals

This project serves as an ongoing exploration of innovative technologies and best practices, applied to create a fully functional blog backend. Key objectives include:

- Showcasing the use of Java 17's features, such as records and pattern matching.
- Illustrating the power of Spring Boot 3.1.2 for building robust applications.
- Demonstrating the seamless integration of Spring Data JPA with PostgreSQL.
- Highlighting strong security features through Spring Security, OpenID, and OAuth2 (Keycloak).
- Providing extensive API documentation with Swagger and springdoc-openapi-ui.
- Conducting rigorous integration testing using RestTemplate.
- Ensuring consistency and reliability with Dockerized testing through TestContainers.
- Maintaining code quality and confidence through AssertJ and Mockito testing libraries.

## Dockerfile
I have created a Dockerfile for the current application. Example of the content of Dockerfile is shown bellow, please do not forget to run the maven build before creating your image: 
 
```
# Use the official openjdk 17 image as the base image
FROM openjdk:17-jdk-slim

COPY --chown=1000:0 maven/app /etc/app
ONBUILD COPY --chown=1000:0 maven/app /etc/app

# Set the working directory in the container
WORKDIR /etc/app

# Expose the port your Spring Boot application will listen on
EXPOSE 8081

ONBUILD HEALTHCHECK CMD curl --fail "http://localhost:$APPLICATION_PORT/health" || exit 1

# Define the command to run your Spring Boot application
ENTRYPOINT [ "java", "-jar", "quickdirtyblog-webapp-0.0.1-SNAPSHOT.jar"]
```

## How to run
first step is to build the project using ```mvn clean package -P docker -DskipTests``` to be able to build the project docker image as well.
then you need to start the docker compose file src/main/docker/compose-local-test.yml file to spin PostgreSql server, and keycloak:

```
docker compose -f src/main/docker/compose-local-test.yml up -d
```
new realm called dirty-blog is imported automatically when running keycloak service, once it started you can navigate to http://localhost:8080/admin to create your user (user admin/admin to authenticate in keycloak admin page).


then run the QuickDirtyBlogApplication.java or alternatively you can create a container from the project image that have been created using maven, make sure you have Docker installed. Then, run the following command:

you can run it with:

```
docker run -dp 8081:8081 dirty-blog:0.0.1-SNAPSHOT
```
## API Definition
Access the definition page: http://localhost:8081/swagger-ui/index.html