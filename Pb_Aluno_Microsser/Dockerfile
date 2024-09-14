FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/Pb_Aluno_Microsser-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "app.jar"]