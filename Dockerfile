FROM openjdk:24-slim AS build

WORKDIR /app
COPY . .

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

FROM openjdk:24-slim
WORKDIR /app
COPY --from=build /target/Web-Structure-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]