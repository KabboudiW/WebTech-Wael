# Build stage
FROM gradle:9.2-jdk21-jammy AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon -x test

# Run stage
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
LABEL authors="waelkabboudi"
ENTRYPOINT ["java","-jar","app.jar"]

