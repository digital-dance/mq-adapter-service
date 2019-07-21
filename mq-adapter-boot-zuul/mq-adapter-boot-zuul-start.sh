#!/bin/bash

mv /jar/mq-adapter-boot-zuul.jar /app/
java -Djava.security.egd=file:/dev/./urandom -jar /app/mq-adapter-boot-zuul.jar