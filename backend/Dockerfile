FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="user"

ENTRYPOINT ["java", "-jar","/app.jar"]