docker run --name ms-seasons -p 81:8080 --network catholicon -td catholicon-ms-seasons
docker network connect logstash ms-seasons
