# -------- Stage 1 : Build avec Maven --------
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copier les fichiers Maven
COPY pom.xml .
COPY src ./src

# Build de l'application
RUN mvn clean package -DskipTests

# -------- Stage 2 : Image légère pour exécuter --------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copier le jar généré depuis le stage builder
COPY --from=builder /app/target/*.jar app.jar

# Port utilisé par l'API
EXPOSE 9000

# Lancer l'application
ENTRYPOINT ["java","-jar","app.jar"]