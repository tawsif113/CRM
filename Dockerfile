FROM gradle:8.3.3-jdk17       AS builder
WORKDIR /home/gradle/project

# 1a) copy only the bits Gradle needs to figure out what to cache
COPY settings.gradle settings.gradle.kts build.gradle build.gradle.kts gradlew ./
COPY gradle              ./gradle

# 1b) copy your code modules
COPY lib1       ./lib1
COPY lib2       ./lib2
COPY application ./application

# 1c) build the Spring‑Boot “fat” JAR for your application module
#     (if you have custom task names or module paths, tweak the project path)
RUN ./gradlew --no-daemon clean :application:bootJar

# ─── 2) RUN STAGE ─────────────────────────────────────────────────────────────
FROM openjdk:17-alpine
WORKDIR /app

# copy the built JAR from the builder
COPY --from=builder \
     /home/gradle/project/application/build/libs/application-*.jar \
     app.jar

# Railway will inject $PORT; tell Spring Boot to use it (fallback 8080)
ENV SERVER_PORT=${PORT:-8080}

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]