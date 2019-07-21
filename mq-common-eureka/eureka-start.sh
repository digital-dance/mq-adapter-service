#!/bin/bash

mv /jar/mq-common-eureka.jar /app/
java -Djava.security.egd=file:/dev/./urandom -jar /app/mq-common-eureka.jar