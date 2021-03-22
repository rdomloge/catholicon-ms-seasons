#!/bin/bash

mvn clean package && \
docker buildx build --platform linux/amd64,linux/arm64\
 -t rdomloge/catholicon-ms-seasons-multiarch:latest . --push
