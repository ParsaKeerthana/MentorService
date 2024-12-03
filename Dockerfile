# Use an official OpenJDK runtime as a parent image# Use a compatible Java 17 runtime
FROM eclipse-temurin:17-jre
ARG JAR_FILE=target/*.jar
COPY ./target/MentorService-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]