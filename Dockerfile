FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
COPY --from=build /target/yash-0.1.1-SNAPSHOT.jar yash.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "yash.jar"]