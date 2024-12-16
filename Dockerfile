FROM openjdk:17-jdk-slim
VOLUME /tmp
EXPOSE 8080
COPY build/libs/bookmanager-1.0.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
