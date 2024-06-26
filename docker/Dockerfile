# Multi-Stage Build
# It optimizes the final image once the JDK image will only be used to generate the binaries

# First stage: Build the application
FROM maven:3.8.5-openjdk-17-slim AS build

# Copy source code and pom.xml
COPY src /app/src
COPY pom.xml /app

# Set working directory
WORKDIR /app

# Build the application
RUN mvn clean install -Dspring.profiles.active=dev

# Second stage: Create the final image
FROM amazoncorretto:17-alpine3.17

# Set working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application's port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]