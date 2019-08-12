#!/bin/sh

#echo '192.168.0.118 www.mq.ingress' >> /etc/hosts
mv /jar/mq-adapter-boot-zuul.jar /app/
java -Djava.security.egd=file:/dev/./urandom -jar /app/mq-adapter-boot-zuul.jar