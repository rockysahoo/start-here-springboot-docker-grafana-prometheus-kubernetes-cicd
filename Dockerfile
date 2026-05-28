# This is the file used by the real time, b/c you can see we're moving everything to the build stage inside the docker, and build the application inside the docker, and then copy only the jar file to the runtime image, which is a best practice for building docker images for Java applications.
# This way, we can keep the final image as small as possible and only include the necessary files to run the application.

# But in the basic one, we are copying the jar file from the target directory, which means we have to build the jar file locally before building the docker image, which is not a good practice, because it can lead to issues with dependencies, compatibility and we need to build the project locally everytime to make sure the latest chages are included in the jar file, which can be time consuming and error prone.


# ── Stage 1: Build ────────────────────────────────────────────────────────────
# Use an official OpenJDK runtime as a parent image, check the Image version with the Java version you are using in your application and configure the same in the Dockerfile.
# This has pulled the OpenJDK 25 image from the Docker Hub (https://hub.docker.com/_/openjdk/tags), which is a lightweight version of the JDK that is suitable for running Java applications in a containerized environment.
FROM eclipse-temurin:25-jdk-alpine AS build_image

ENV APP_HOME=/app

# Workdir creation
# Set the working directory in the container (A folder inside the container where the application will be stored).
WORKDIR $APP_HOME

# Alpine uses apk as its package manager final image. The maven package is installed to build the Java application.
RUN apk add --no-cache maven

# Copy the POM first so Maven can resolve dependencies independently of source
# changes — Docker layer caching will skip this step on subsequent builds when
# only source files change.
COPY pom.xml           $APP_HOME/
RUN mvn dependency:go-offline -B
# Copy the rest of the source tree and build.
COPY .gitattributes   $APP_HOME/
COPY .gitignore       $APP_HOME/
COPY mvnw             $APP_HOME/
COPY src              $APP_HOME/src
RUN mvn clean install -DskipTests -B
COPY target            $APP_HOME/target/

# Copy the jar file from the target directory to the container and rename it to app.jar. This is the jar file that will be run when the container starts.
# This for, you need to build the jar file locally and then copy it to the container.
# COPY  target/peer-study-0.0.1-dev.jar app.jar

# ── Stage 2: Runtime ──────────────────────────────────────────────────────────
# Use a separate, minimal JRE-only image for the final artifact.
# The build_image stage (JDK + Maven + sources) is now discarded, keeping the shipped image as small and secure as possible.
FROM eclipse-temurin:25-jre-alpine

ENV APP_HOME=/app
WORKDIR $APP_HOME

# Pull only the compiled jar from the build stage — nothing else travels forward.
COPY --from=build_image $APP_HOME/target/peer-study-0.0.1-dev.jar app.jar

# Expose the Spring Boot Port that the application will run on.
# In this case, we are exposing port 8081, which is the default port for Spring Boot applications. This allows us to access the application from outside the container when it is running.
EXPOSE 8081

# Container entrypoint, Run the jar file when the container starts.
# The ENTRYPOINT instruction specifies the command that will be run when the container starts, and in this case, we are using the java -jar command to run the app.jar file that we copied earlier.
# This will start the Spring Boot application when the container is run.
ENTRYPOINT ["java", "-jar", "app.jar"]
