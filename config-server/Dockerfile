FROM java:8
#VOLUME /app
RUN mkdir -p /jar

RUN mkdir -p /app/logs/config-server

ARG BUILD_WORKDIR

ARG JAR_FILE
ARG SH_FILE
ARG SERVER_PORT

RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}
ADD ${JAR_FILE} /jar/config-server.jar

COPY ${SH_FILE} /jar/config-server-start.sh

EXPOSE ${SERVER_PORT}

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-common-eureka.jar"]
RUN chmod -R 777 /jar/config-server-start.sh
RUN chmod -R 777 /app
ENTRYPOINT ["/jar/config-server-start.sh"]