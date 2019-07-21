#!/bin/bash

mv /jar/config-server.jar /app/
java -Djava.security.egd=file:/dev/./urandom -jar /app/config-server.jar