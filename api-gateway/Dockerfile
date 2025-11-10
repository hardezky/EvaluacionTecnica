# Etapa 1: Compilación con Maven (usa los mismos nombres)
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final (tu formato original)
FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY --from=build /app/${JAR_FILE} api-gateway.jar
EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=docker
#ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
ENTRYPOINT ["sh", "-c", "sleep 10 && java -jar api-gateway.jar"]

