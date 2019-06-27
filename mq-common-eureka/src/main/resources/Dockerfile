FROM java:8
#VOLUME /app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/mq-common-eureka
ADD mq-common-eureka-1.0.0-SNAPSHOT.jar /jar/mq-common-eureka.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-common-eureka.jar"]