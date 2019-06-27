FROM java:8
#VOLUME /app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/mq-adapter-boot-rest-provider
EXPOSE 2222
ADD mq-adapter-boot-rest-provider-1.0.0-SNAPSHOT.jar /jar/mq-adapter-boot-rest-provider.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-rest-provider.jar"]