# Stage 1: Build stage
FROM maven:3.8.5-openjdk-17-slim AS builder

# Set the working directory
WORKDIR /app

# Copy the pom.xml file and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code and build the application
COPY src ./src
RUN mvn package -D skipTests

# Stage 2: Runtime stage
FROM openjdk:17-jdk-slim

# Create a non-root user and group
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set the working directory
WORKDIR /app

# Copy the built application from the builder stage using a wildcard
COPY --from=builder /app/target/voting-0.0.1-SNAPSHOT.jar voting.jar

# Set the ownership of the application directory to the non-root user
RUN chown -R appuser:appuser /app

# Switch to the non-root user
USER appuser

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "voting.jar"]
