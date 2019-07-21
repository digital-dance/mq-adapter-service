FROM java:8
#VOLUME /app
RUN mkdir -p /jar

RUN mkdir -p /app/logs/mq-common-eureka

ARG BUILD_WORKDIR

ARG JAR_FILE
ARG SH_FILE
ARG SERVER_PORT

RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}
ADD ${JAR_FILE} /jar/mq-common-eureka.jar

COPY ${SH_FILE} /jar/eureka-start.sh

EXPOSE ${SERVER_PORT}

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-common-eureka.jar"]
RUN chmod -R 777 /jar/eureka-start.sh
RUN chmod -R 777 /app
ENTRYPOINT ["/jar/eureka-start.sh"]