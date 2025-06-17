# ─── 1) BUILD STAGE ───────────────────────────────────────────────────────────
FROM openjdk:17-jdk-slim AS builder
WORKDIR /app

# Copy wrapper + config first for caching
COPY gradlew settings.gradle build.gradle settings.gradle.kts build.gradle.kts ./
COPY gradle ./gradle

# Copy your code
COPY lib1       ./lib1
COPY lib2       ./lib2
COPY application ./application

# Make the wrapper executable & build
RUN chmod +x gradlew \
 && ./gradlew --no-daemon clean :application:bootJar

# ─── 2) RUN STAGE ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre
# this tag definitely exists
WORKDIR /app

COPY --from=builder /app/application/build/libs/application-*.jar app.jar

ENV SERVER_PORT=${PORT:-8080}
EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]