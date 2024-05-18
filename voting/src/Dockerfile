# Stage 1: Build the application with Maven
FROM maven:3.9.1-eclipse-temurin-19 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and any other necessary files to the container
COPY pom.xml .

# Copy the source code to the container
COPY src ./src

# Package the application, skipping tests
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM eclipse-temurin:19-jdk-jammy

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime stage
COPY --from=build /app/target/app.jar .

# Expose port 8080
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]
