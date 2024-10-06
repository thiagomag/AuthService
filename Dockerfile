FROM gradle:8.10.2-jdk-21-and-22 AS build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]