#FROM maven:3.8.1-openjdk-17 AS build
#WORKDIR /app
#COPY pom.xml .
#RUN mvn -B -e -C -T 1C verify --fail-never
#COPY src src
#RUN mvn -B -e -o -T 1C package -U -DskipTests
#
#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY --from=build /app/${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.8.1-openjdk-17

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Copy the JAR file to the root directory
RUN cp target/demo-0.0.1-SNAPSHOT.jar /interview-service.jar

# Use the JAR file in the ENTRYPOINT command
ENTRYPOINT ["java", "-jar", "/interview-service.jar"]
