
# Use OpenJDK base image
FROM openjdk:21-jdk-slim

# Add a volume for logs (optional)
VOLUME /tmp

# Set working directory
WORKDIR /app

# Copy jar
COPY target/*.jar app.jar

# Expose application port (optional)
EXPOSE 8081

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]
