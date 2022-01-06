FROM openjdk:15-jdk-alpine
VOLUME /tmp
EXPOSE 27017
EXPOSE 8052
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]