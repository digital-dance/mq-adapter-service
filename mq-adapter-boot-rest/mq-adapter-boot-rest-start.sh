#!/bin/sh

mv /jar/mq-adapter-boot-rest.jar /app/
java -Djava.security.egd=file:/dev/./urandom -jar /app/mq-adapter-boot-rest.jar