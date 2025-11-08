FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/solicitudes-ms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} solicitudes-ms.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "solicitudes-ms.jar"]