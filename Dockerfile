FROM openjdk:8

ENV PROFILE=default

WORKDIR /app

COPY target/rest-api-0.0.1-SNAPSHOT.jar /app/spring-app.jar

ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar", "spring-app.jar"]
