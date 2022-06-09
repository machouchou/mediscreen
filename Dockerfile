FROM eclipse-temurin:11.0.14_9-jre-alpine as builder
WORKDIR application
COPY target/mediscreen-0.0.4-SNAPSHOT.jar  mediscreen-1.0.0.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "mediscreen-1.0.0.jar"]
