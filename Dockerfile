FROM gradle:8.4.0-jdk17 AS builder

WORKDIR /app
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

RUN gradle build --no-daemon


FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080