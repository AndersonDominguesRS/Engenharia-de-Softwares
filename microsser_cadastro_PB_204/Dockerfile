FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/microsser_cadastro_PB_204-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]