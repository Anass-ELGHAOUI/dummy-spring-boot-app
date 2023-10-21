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
# Use the official AdoptOpenJDK 17 image as the base image
FROM adoptopenjdk/openjdk17:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/quickdirtyblog-webapp-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application will listen on
EXPOSE 8081

# Define the command to run your Spring Boot application
ENTRYPOINT [ "java", "-jar", "app.jar"]
```

## How to run
first step is use the compose-local-test.yml file to spin PostgreSql server, and keycloak:

```
docker compose -f compose-local-test.yml up -d
```
after connecting to keycloak admin from http://localhost:8080/admin you can create a new realm (name it dirty-blog) by importing the file dummy-app/src/main/resources/realm-export.json then create a new user.

then run the QuickDirtyBlogApplication.java or alternatively you can build a Docker image from the project Dockerfile, make sure you have Docker installed and navigate to the directory containing the Dockerfile and your Spring Boot application JAR file. Then, run the following command:

```
docker build -t your-image-name:your-tag .
```
Replace your-image-name and your-tag with the desired name and tag for your Docker image. Once the image is built, you can run it with:

```
docker run -dp 8081:8081 your-image-name:your-tag
```
## API Definition
Access the definition page: http://localhost:8081/swagger-ui/index.html