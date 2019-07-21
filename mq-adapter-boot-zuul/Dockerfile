FROM java:8
#VOLUME /app
RUN mkdir -p /jar

RUN mkdir -p /app/logs/mq-adapter-boot-zuul

ARG BUILD_WORKDIR

ARG JAR_FILE
ARG SH_FILE
ARG SERVER_PORT

RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}
ADD ${JAR_FILE} /jar/mq-adapter-boot-zuul.jar

COPY ${SH_FILE} /jar/eureka-start.sh

EXPOSE ${SERVER_PORT}

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-zuul.jar"]
RUN chmod -R 777 /jar/mq-adapter-boot-zuul-start.sh
RUN chmod -R 777 /app
ENTRYPOINT ["/jar/mq-adapter-boot-zuul-start.sh"]