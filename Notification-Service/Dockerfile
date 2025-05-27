FROM openjdk:21-jdk-slim
WORKDIR /app

#COPY target/*.jar app.jar
COPY . .
RUN ./mvnw clean package -DskipTests
EXPOSE 8083
ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]