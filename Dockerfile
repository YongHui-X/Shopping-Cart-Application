FROM node:22-alpine AS frontend-build

WORKDIR /workspace/shoppingcartfrontend

COPY shoppingcartfrontend/package*.json ./
RUN npm ci

COPY shoppingcartfrontend/ ./
RUN npm run build

FROM maven:3.9.9-eclipse-temurin-17 AS backend-build

WORKDIR /workspace

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./
RUN chmod +x mvnw && ./mvnw -B -DskipTests dependency:go-offline

COPY src src
COPY --from=frontend-build /workspace/shoppingcartfrontend/build src/main/resources/static/app
RUN ./mvnw -B -DskipTests package

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring

COPY --from=backend-build /workspace/target/*.jar /app/app.jar

USER spring

EXPOSE 8080

ENV JAVA_OPTS="-Xms96m -Xmx320m -XX:MaxMetaspaceSize=128m -XX:ReservedCodeCacheSize=48m -XX:MaxDirectMemorySize=32m -XX:+UseSerialGC -XX:+ExitOnOutOfMemoryError -XX:TieredStopAtLevel=1"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
