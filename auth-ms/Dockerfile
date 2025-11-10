FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/auth-ms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} auth-ms.jar
EXPOSE 8083
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "auth-ms.jar"]