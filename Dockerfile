FROM openjdk:11.0-jdk-slim

COPY target/catholicon-ms-seasons.jar catholicon-ms-seasons.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "catholicon-ms-seasons.jar"]
