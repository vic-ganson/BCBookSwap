# Stage 1: Build the Java project
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Copy Maven config and source code
COPY pom.xml .
COPY src ./src

# Install Maven and build
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Stage 2: Run the Java application
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built JAR from the build stage
COPY --from=build /app/target/HackTheHeightsProject-1.0.0.jar app.jar

# Expose port (Render sets PORT env variable)
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java","-jar","app.jar"]
