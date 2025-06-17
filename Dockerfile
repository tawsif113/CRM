FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

# 1a) Copy wrapper and build scripts for caching
COPY gradlew settings.gradle build.gradle ./
COPY gradle ./gradle

# 1b) Copy your modules
COPY lib1        ./lib1
COPY lib2        ./lib2
COPY application ./application

# 1c) Make the wrapper executable & build the fat JAR
RUN chmod +x gradlew \
 && ./gradlew --no-daemon clean :application:bootJar

# ─── 2) RUNTIME STAGE ───────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Copy only the Spring Boot JAR from the builder stage
COPY --from=builder /app/application/build/libs/application-*.jar app.jar

# Tell Spring Boot to bind to Railway’s $PORT (fallback to 8080 locally)
ENV SERVER_PORT=${PORT:-8080}
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]