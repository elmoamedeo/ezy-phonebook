# Use an official Java runtime as a parent image
FROM adoptopenjdk/openjdk11

# Set the working directory to /app
WORKDIR /back-app

# Copy the current directory contents into the container at /app
COPY . /back-app

# Set the environment variables
ENV SPRING_DATASOURCE_URL jdbc:postgresql://db:5432/phonebook
ENV SPRING_DATASOURCE_USERNAME postgres
ENV SPRING_DATASOURCE_PASSWORD postgres

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the application when the container starts
CMD ["./gradlew", "bootRun"]
