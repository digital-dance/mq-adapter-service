FROM java:8
#VOLUME /app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/mq-adapter-boot-zuul
EXPOSE 8670
ADD mq-adapter-boot-zuul-1.0.0-SNAPSHOT.jar /jar/mq-adapter-boot-zuul.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-zuul.jar"]