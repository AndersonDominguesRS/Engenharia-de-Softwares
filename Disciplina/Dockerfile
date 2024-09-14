FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Disciplina-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8071
ENTRYPOINT ["java", "-jar", "app.jar"]