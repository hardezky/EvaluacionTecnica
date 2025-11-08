FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/clientes-ms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} clientes-ms.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "clientes-ms.jar"]