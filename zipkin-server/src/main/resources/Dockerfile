FROM java:8
#VOLUME /app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/zipkin-server
ADD zipkin-server-1.0.0-SNAPSHOT.jar /jar/zipkin-server.jar
EXPOSE 7082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/zipkin-server.jar"]