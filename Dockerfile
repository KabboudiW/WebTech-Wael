# Build stage
FROM gradle:9.2-jdk17-jammy AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon

# Run stage
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
LABEL authors="waelkabboudi"
ENTRYPOINT ["java","-jar","app.jar"]

