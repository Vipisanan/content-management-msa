
# Use OpenJDK base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar
#COPY target/*.jar app.jar
COPY . .
RUN ./mvnw clean package -DskipTests

# Expose application port (optional)
EXPOSE 8081

# Run the application
ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]
