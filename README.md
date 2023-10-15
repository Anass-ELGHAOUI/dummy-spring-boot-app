# dummy-app
this is a dummy blog backend app, the objective here is to develop a dummy app on which I will use different technologies I've learned during my experiences and also by using the best practices in each technology.

## technologies that have been used until this moment:
- Java 17 with the usage of records and pattern matching
- Spring boot 3 as java framework
- Spring data jpa
- PostgreSql as a sql database
- Spring security using OpenId and OAuth2 (okta)
- usage of springdoc-openapi-ui to expose the swagger documentation
- integration test with the usage of Rest template
- TestContainers to run a PostgreSql image locally when starting the integration test
- assertj library and mockito for the test part

## Dockerfile
I have created a Dockerfile for the curent application. Exemple of the content of Dockerfile is shown bellow, please do not forget to run the maven build before creating your image: 
 
```
# Use the official AdoptOpenJDK 17 image as the base image
FROM adoptopenjdk/openjdk17:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/your-spring-boot-app.jar /app/app.jar

# Expose the port your Spring Boot application will listen on
EXPOSE 8080

# Define the command to run your Spring Boot application
ENTRYPOINT [ "java", "-jar", "app.jar"]
```

## How to run
first step is use the compose-local-test.yml file to spin a PostgreSql server:

```
docker compose -f compose-local-test.yml up -d
```

the run the QuickDirtyBlogApplication.java or alternatively you can build a Docker image from the project Dockerfile, make sure you have Docker installed and navigate to the directory containing the Dockerfile and your Spring Boot application JAR file. Then, run the following command:

```
docker build -t your-image-name:your-tag .
```
Replace your-image-name and your-tag with the desired name and tag for your Docker image. Once the image is built, you can run it with:

```
docker run -dp 8080:8080 your-image-name:your-tag
```
