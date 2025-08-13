# ===== Build =====
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
# Maven:
RUN ./mvnw -q -DskipTests package
# (si usas Gradle, reemplaza por:)
# RUN ./gradlew -q clean bootJar

# ===== Runtime =====
FROM eclipse-temurin:21-jre
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -Dserver.port=8080"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]