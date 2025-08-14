# ====== Etapa de build ======
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /workspace/app

# Copia solo lo necesario para cachear dependencias
COPY pom.xml .
RUN mvn -q -e -DskipTests dependency:go-offline

# Copia el código y compila
COPY src ./src
RUN mvn -q -e -DskipTests package

# ====== Etapa de runtime ======
FROM eclipse-temurin:21-jre
WORKDIR /app
# Copia el jar construido
COPY --from=build /workspace/app/target/*-SNAPSHOT.jar app.jar
# Si tu jar no es SNAPSHOT, usa: COPY --from=build /workspace/app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
