FROM java:8
#VOLUME /app
RUN mkdir -p /jar

RUN mkdir -p /app/logs/mq-adapter-boot-rest-provider

ARG BUILD_WORKDIR

ARG JAR_FILE
ARG SH_FILE
ARG SERVER_PORT

RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}
ADD ${JAR_FILE} /jar/mq-adapter-boot-rest-provider.jar

COPY ${SH_FILE} /jar/mq-adapter-boot-rest-provider-start.sh

EXPOSE ${SERVER_PORT}

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-rest-provider.jar"]
RUN chmod -R 777 /jar/mq-adapter-boot-rest-provider-start.sh
RUN chmod -R 777 /app
ENTRYPOINT ["/jar/mq-adapter-boot-rest-provider-start.sh"]