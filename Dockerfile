FROM openjdk:8-jre-alpine3.8
RUN apk add libc6-compat
RUN apk add curl

RUN curl -L -O https://artifacts.elastic.co/downloads/beats/filebeat/filebeat-6.5.4-linux-x86_64.tar.gz
RUN tar xzvf filebeat-6.5.4-linux-x86_64.tar.gz
RUN rm filebeat-6.5.4-linux-x86_64.tar.gz
RUN rm filebeat-6.5.4-linux-x86_64/filebeat.reference.yml
COPY filebeat.yml filebeat-6.5.4-linux-x86_64/filebeat.yml

COPY wrapper_script.sh wrapper_script.sh
COPY target/catholicon-ms-seasons.jar catholicon-ms-seasons.jar
ENTRYPOINT ["sh", "./wrapper_script.sh"]
EXPOSE 8080
