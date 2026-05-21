# Use an official OpenJDK runtime as a parent image, check the Image version with the Java version you are using in your application and configure the same in the Dockerfile.
# This has pulled the OpenJDK 25 image from the Docker Hub (https://hub.docker.com/_/openjdk/tags), which is a lightweight version of the JDK that is suitable for running Java applications in a containerized environment.
FROM eclipse-temurin:25-jdk-alpine AS build_image

ENV APP_HOME=/app

# Workdir creation
# Set the working directory in the container (A folder inside the container where the application will be stored).
WORKDIR $APP_HOME

# Alpine uses apk as its package manager final image. The maven package is installed to build the Java application.
RUN apk add --no-cache maven

COPY pom.xml           $APP_HOME/
RUN mvn dependency:go-offline -B
COPY .gitattributes   $APP_HOME/
COPY .gitignore       $APP_HOME/
COPY mvnw             $APP_HOME/
COPY src              $APP_HOME/src
RUN mvn clean install -DskipTests -B


# Copy the jar file from the target directory to the container and rename it to app.jar. This is the jar file that will be run when the container starts.
COPY  target/peer-study-0.0.1-dev.jar app.jar

# Expose the port that the application will run on.
# In this case, we are exposing port 8081, which is the default port for Spring Boot applications. This allows us to access the application from outside the container when it is running.
EXPOSE 8081

# Container entrypoint, Run the jar file when the container starts.
# The ENTRYPOINT instruction specifies the command that will be run when the container starts, and in this case, we are using the java -jar command to run the app.jar file that we copied earlier.
# This will start the Spring Boot application when the container is run.
ENTRYPOINT ["java", "-jar", "app.jar"]


# Install curl in a separate stage to keep the final image small and secure. This stage is used for testing or debugging purposes, and it does not include the Java runtime, which reduces the attack surface of the final image.
