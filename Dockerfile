FROM maven:3.9.9-eclipse-temurin-21-alpine

RUN apk update

RUN adduser -D userapp

USER userapp

WORKDIR /app

COPY --chown=userapp:userapp . .

RUN mvn clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
