# Use the official Node.js image based on Alpine Linux for a lightweight build.
# Tag 22.10.0 is specified, and this stage is named 'base' for multi-stage builds.
FROM node:22.10.0-alpine3.20 AS base

# Set the working directory inside the container to /app.
WORKDIR /app

# Copy package.json and package-lock.json (if available) to the working directory.
# This allows npm install to cache dependencies efficiently.
COPY package*.json ./

# Install the project dependencies defined in package.json.
RUN npm install

# Copy the entire application code into the working directory in the container.
COPY . .

# Start a new stage using the same base image to create a smaller final image.
FROM node:22.10.0-alpine3.20

# Set the working directory in the new stage to /app.
WORKDIR /app

# Copy the built application from the 'base' stage to the working directory in this new stage.
COPY --from=base /app .

# Expose port 3000 to allow external access to the application.
EXPOSE 3000

# Define the command to start the application using npm.
CMD ["npm", "start"]