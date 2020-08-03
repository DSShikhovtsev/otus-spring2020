FROM openjdk:11-jre-slim
COPY /target/homework-docker.jar /app/docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/docker.jar"]
