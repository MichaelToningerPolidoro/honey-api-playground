# Architecture

First of all, thank you for taking a look at this documentation :)

The Honey API Playground is a single Spring Boot application organized using a layered architecture.

## 1. High-Level Structure

The main application components can also be represented as follows:

```mermaid
flowchart TD
    Client --> Security[Spring Security]
    Security --> Controller[REST Controller]
    Controller --> Service[Service Layer]
    Service --> Repository[Repository Layer]
    Repository --> Database[(H2 or PostgreSQL)]
```

The application also uses DTOs, validation components, exception handlers, and cross-cutting concerns for token validation.

## 2. Main Packages

### 2.1 `controller`

Defines the REST endpoints and receives HTTP requests.

### 2.2 `service`

Contains the main business rules and coordinates operations between controllers and repositories.

### 2.3 `repository`

Provides persistence access through Spring Data JPA.

### 2.4 `domain`

Contains the entities persisted in the database, such as users, hobbies, countries, and programming time options.

### 2.5 `dto`

Contains request, response, and model objects used to control the data exchanged through the API.

### 2.6 `validation`

Contains reusable validation logic for request fields and custom validation rules.

### 2.7 `handler` and `exception`

Define the application's custom exceptions and translate them into HTTP responses.

### 2.8 `aspect` and `annotation`

Contain the custom annotation and AspectJ logic used to validate the JWT token before protected user operations.

## 3. Domain Model

The main domain relationships are structured as follows:

```mermaid
erDiagram
    USER ||--|| PASSWORD : has
    USER ||--|| BORN_DATA : has
    BORN_DATA ||--|| COUNTRY : references
    USER }o--|| PROGRAMMING_TIME_OPTION : selects
    USER }o--o{ HOBBY : has
```

The same structure can be represented in a simpler hierarchical view:

```text
User
 ├── 1:1 Password
 ├── 1:1 BornData
 │       └── 1:1 Country
 ├── N:1 ProgrammingTimeOption
 └── N:N Hobby
```

The `User` entity is the central domain object. It is related to:

- One `Password` entity.
- One `BornData` entity.
- One `ProgrammingTimeOption` entity, which can be shared by multiple users.
- Multiple `Hobby` entities.

The `BornData` entity is also related to one `Country` entity. Countries, hobbies, and programming time options act as supporting reference data used during user registration and update operations.

## 4. Application Style

The project is a monolithic application with separated internal layers. The current structure is intended to keep responsibilities organized while remaining simple enough for learning and experimentation.

[Back to README](../README.md)
