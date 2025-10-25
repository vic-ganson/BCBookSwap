# Use official Java 17 image
FROM eclipse-temurin:17-jdk-jammy

# Set working directory
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the project inside the container
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Copy the built jar
COPY target/HackTheHeightsProject-1.0.0.jar app.jar

# Expose the port (Render will assign PORT env)
EXPOSE 8080

# Run the jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
