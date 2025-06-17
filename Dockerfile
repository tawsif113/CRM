FROM gradle:7.6-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew :application:bootJar

# Stage 2: Run the application
FROM openjdk:17-slim
WORKDIR /app
COPY --from=builder /app/application/build/libs/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]