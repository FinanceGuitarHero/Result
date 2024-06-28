FROM maven:3.8.4-openjdk-17 as build

LABEL authors="denmo"

WORKDIR /app

COPY src src
COPY pom.xml .

RUN mvn package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]