# Use an official OpenJDK runtime as a parent image, check the Image version with the Java version you are using in your application and configure the same in the Dockerfile.
# This has pulled the OpenJDK 25 image from the Docker Hub (https://hub.docker.com/_/openjdk/tags), which is a lightweight version of the JDK that is suitable for running Java applications in a containerized environment.
FROM eclipse-temurin:25 AS build_image
# Set the working directory in the container (A folder inside the container where the application will be stored).
WORKDIR /app
# Copy the jar file from the target directory to the container and rename it to app.jar. This is the jar file that will be run when the container starts.
COPY target/peer-study-0.0.1-dev.jar app.jar
# Expose the port that the application will run on.
# In this case, we are exposing port 8081, which is the default port for Spring Boot applications. This allows us to access the application from outside the container when it is running.
EXPOSE 8081
# Run the jar file when the container starts.
# The ENTRYPOINT instruction specifies the command that will be run when the container starts, and in this case, we are using the java -jar command to run the app.jar file that we copied earlier.
# This will start the Spring Boot application when the container is run.
ENTRYPOINT ["java", "-jar", "app.jar"]


# Install curl in a separate stage to keep the final image small and secure. This stage is used for testing or debugging purposes, and it does not include the Java runtime, which reduces the attack surface of the final image.
FROM alpine:latest
RUN apk update && apk add curl


# Workdir creation dummy purpose insid the docker container, this is not used in the actual application,
# but it is necessary to create the directory structure for the application to run properly.
# The WORKDIR instruction sets the working directory for any subsequent instructions in the Dockerfile, and it also creates the directory if it does not exist.
# In this case, we are creating a directory called /app/test, which will be used as the working directory for the application when it is run in the container.
# This allows us to keep our application files organized and separate from other files in the container, and it also makes it easier to manage and maintain our application in a containerized environment.
# Env variables
ENV APP_HOME=/app/test
WORKDIR $APP_HOME
COPY --from=build_image $APP_HOME/build/libs/ .