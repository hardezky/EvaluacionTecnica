# Etapa 1: Compilación con Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/solicitudes-ms-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY --from=build /app/${JAR_FILE} solicitudes-ms.jar
EXPOSE 8081
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT ["java", "-jar", "solicitudes-ms.jar"]
