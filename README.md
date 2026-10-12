# Honey API Playground

First of all, thank you for taking a look at this project :)

This is a learning project using Spring Boot to gain a better understanding of this framework and backend development in general.

In a nutshell, this project provides a REST API for user management, with authentication, validation, relationships between entities, and some supporting data resources.

## Objectives

The main objective of this project is to practice backend development concepts by building a complete API with Spring Boot.

It was also created to explore different approaches to authentication, data validation, persistence, testing, and code organization in a backend application.

## About the Project

### Technologies and Tools

The main technologies and tools used in this project are:

- Java 17
- Spring Boot 2.6.15
- Spring Data JPA
- Spring Security
- AspectJ and Spring AOP
- JWT
- JUnit
- Mockito
- H2 in-memory database
- PostgreSQL
- Maven
- Postman
- Insomnia

### Architecture

This project is organized using a layered architecture, separating controllers, services, repositories, domains, DTOs, validations, and exception handling.

The application is a single Spring Boot application with different API resources, rather than a set of independent microservices.

For more details, see the [architecture documentation](docs/architecture.md).

### Features

The project currently includes:

- User registration
- User search, update, and deletion
- Login with JWT token generation
- HTTP Basic authentication for API access
- Hobbies, countries, and programming time options lookup
- Request and domain data validation
- Custom exception handling
- Unit tests for controllers, services, and repositories

The complete endpoint documentation, including request and response examples, is available in the [API documentation](docs/api.md).

### Profiles and Configuration

The default application profile is **local**, which uses an H2 in-memory database and predefined local data.

The **dev** profile can be used with PostgreSQL.

More details about profiles, environment variables, and database configuration are available in the [configuration documentation](docs/configuration.md).

## Running the Project ✅

To run this project, you'll need the following tools:

- JDK 17
- Maven, or a compatible Maven installation
- PostgreSQL 14.0+ (Only required for the `dev` application profile)

The project includes a Maven Wrapper, so Maven does not need to be installed globally.

To run the tests:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

To start the application using the default **local** profile:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

After starting the application, the API will be available at:

```text
http://localhost:8080
```

## Concepts Learned 📚

Key concepts learned from this project:

- Fundamental Spring Boot concepts
- Development of RESTful CRUD operations
- Backend development
- Authentication and authorization
- Data validation using DTOs and domains
- Establishing relationships between tables using Spring Data JPA
- Layered architecture
- Unit testing for controllers, services, and repositories
- Use of mocks in unit tests with Mockito
- Aspect-Oriented Programming (AOP)
- Applying AOP with AspectJ and Spring Boot

## Collections 📂

You can import the Postman or Insomnia collections to test requests and perform user operations and login in the project.

The collections are available in the `.collections` folder:

- [Postman collection](.collections/honey-api-playground.postman_collection.json)
- [Insomnia collection](.collections/honey-api-playground-insomnia.yaml)

The collections include examples of successful requests and some invalid request scenarios.

## License

This project is licensed under the terms described in the [LICENSE](LICENSE) file.
