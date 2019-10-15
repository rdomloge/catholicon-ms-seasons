FROM arm32v7/openjdk:10.0.1-jdk-slim

# Enable us to build ARM images in an i386 environment
COPY qemu-arm-static /usr/bin/qemu-arm-static

COPY target/catholicon-ms-seasons.jar catholicon-ms-seasons.jar

EXPOSE 8080

ENTRYPOINT ["/usr/bin/java", "-jar", "catholicon-ms-seasons.jar"]
