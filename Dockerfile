# Use the official AdoptOpenJDK 17 image as the base image
FROM adoptopenjdk/openjdk17:alpine-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/quickdirtyblog-webapp-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port your Spring Boot application will listen on
EXPOSE 8080

# Define the command to run your Spring Boot application
ENTRYPOINT [ "java", "-jar", "app.jar"]