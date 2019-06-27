FROM java:8
#VOLUME /home/app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/mq-adapter-boot-rest
EXPOSE 1111
ADD mq-adapter-boot-rest-1.0.0-SNAPSHOT.jar /jar/mq-adapter-boot-rest.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-rest.jar"]