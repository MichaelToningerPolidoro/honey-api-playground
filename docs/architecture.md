# Architecture

First of all, thank you for taking a look at this documentation :)

The Honey API Playground is a single Spring Boot application organized using a layered architecture.

## High-Level Structure

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

The application also uses DTOs, validation components, exception handlers, and cross-cutting concerns for token validation.

## Main Packages

### `controller`

Defines the REST endpoints and receives HTTP requests.

### `service`

Contains the main business rules and coordinates operations between controllers and repositories.

### `repository`

Provides persistence access through Spring Data JPA.

### `domain`

Contains the entities persisted in the database, such as users, hobbies, countries, and programming time options.

### `dto`

Contains request, response, and model objects used to control the data exchanged through the API.

### `validation`

Contains reusable validation logic for request fields and custom validation rules.

### `handler` and `exception`

Define the application's custom exceptions and translate them into HTTP responses.

### `aspect` and `annotation`

Contain the custom annotation and AspectJ logic used to validate the JWT token before protected user operations.

## Application Style

The project is a monolithic application with separated internal layers. The current structure is intended to keep responsibilities organized while remaining simple enough for learning and experimentation.

[Back to README](../README.md)
