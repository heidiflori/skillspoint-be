FROM maven:3.8.4-openjdk-17-slim

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests

ENTRYPOINT ["java", "-jar", "target/spring-boot-security-jwt-0.0.1-SNAPSHOT.jar"]
