FROM java:8
#VOLUME /app
RUN mkdir -p /jar
RUN mkdir -p /app/logs/config-server
ADD config-server-1.0.0-SNAPSHOT.jar /jar/config-server.jar
EXPOSE 8762
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/config-server.jar"]