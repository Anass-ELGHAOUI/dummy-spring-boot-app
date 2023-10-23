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