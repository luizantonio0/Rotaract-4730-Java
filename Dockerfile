FROM openjdk:24-slim AS build

# Define o diretório de trabalho
WORKDIR /app

# Instala Maven
RUN apt-get update && apt-get install -y maven

# Copia o projeto para o container
COPY . .

# Compila o projeto (sem rodar testes)
RUN mvn clean install -DskipTests

# ----------- Runtime -----------
FROM openjdk:24-slim
WORKDIR /app

# Copia o jar gerado no build
COPY --from=build /app/target/Web-Structure-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]