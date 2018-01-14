#!/bin/sh

docker-compose -f container/docker-compose.yml up -d
sleep 5
sbt test
docker-compose -f container/docker-compose.yml down