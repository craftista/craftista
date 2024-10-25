# Stage 1: Build stage
FROM golang:1.20 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy Go module files and download dependencies first
COPY go.mod go.sum ./
RUN go mod download

# Copy the rest of the application code
COPY . .

# Build the Go application
RUN go build -o recommendation_app

# Stage 2: Runtime stage
FROM golang:1.20

# Set the working directory for the runtime stage
WORKDIR /app

# Copy the built binary from the builder stage
COPY --from=builder /app/recommendation_app .

# Copy the templates and config.json to the runtime stage
COPY --from=builder /app/templates ./templates
COPY --from=builder /app/config.json ./config.json

# Expose the port your app runs on
EXPOSE 8080

# Set the command to run your application
CMD ["./recommendation_app"]