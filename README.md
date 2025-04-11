# MongoDB Project

This project demonstrates how to integrate MongoDB with a Java application. It includes configuration, models, repositories, and integration tests to manage and test MongoDB operations.

## Features

- **MongoDB Connection**: Configured using an `application.properties` file for flexibility.
- **Models**: Includes a `User` model and an `Address` model to represent data.
- **Repositories**: Provides a `UserRepository` for database operations.
- **Integration Tests**: Validates the functionality of the repository with JUnit.

## Configuration

The MongoDB connection is configured in the `application.properties` file:

```ini
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=practiceDB
```

The connection details are loaded dynamically in the `MongoDBConnection` class.

## Project Structure

- **`src/main/java`**: Contains the main application code.
    - `org.mongodbproject.config.MongoDBConnection`: Manages the MongoDB connection.
    - `org.mongodbproject.models`: Contains the `User` and `Address` models.
    - `org.mongodbproject.repositories.UserRepository`: Handles database operations.
- **`src/test/java`**: Contains integration tests.
    - `org.mongodbproject.integration.UserRepositoryTest`: Tests the repository functionality.

## Usage

1. **Run the Application**: Ensure MongoDB is running locally on the configured host and port.
2. **Insert and Retrieve Data**: Use the `UserRepository` to perform CRUD operations.
3. **Run Tests**: Execute the integration tests to validate the repository.

## Requirements

- Java
- Maven
- MongoDB

## How to Run

1. Clone the repository.
2. Configure MongoDB in the `application.properties` file.
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```
4. Run the tests:
   ```bash
   mvn test
   ```