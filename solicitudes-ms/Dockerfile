FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/solicitudes-ms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} solicitudes-ms.jar
EXPOSE 8081
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "solicitudes-ms.jar"]