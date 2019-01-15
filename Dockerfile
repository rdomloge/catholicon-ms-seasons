#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jdk
COPY target/catholicon-ms-seasons.jar catholicon-ms-seasons.jar
#RUN apk add curl
#RUN apk add bash
RUN apt-get update && apt-get install -y vim
RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.5.4-linux-x86_64.tar.gz
RUN tar xzvf filebeat-6.5.4-linux-x86_64.tar.gz
RUN rm filebeat-6.5.4-linux-x86_64.tar.gz
RUN rm filebeat-6.5.4-linux-x86_64/filebeat.reference.yml
COPY filebeat.yml filebeat-6.5.4-linux-x86_64/filebeat.yml
ENTRYPOINT ["java", "-jar", "catholicon-ms-seasons.jar", " -Djava.util.logging.manager=org.apache.logging.log4j.jul.LogManager"]
EXPOSE 8080
