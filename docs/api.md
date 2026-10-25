# API Documentation

First of all, thank you for taking a look at this documentation :)

This document describes the endpoints currently available in the Honey API Playground project.

## 1. Base URL

When running the application locally, the base URL is:

```text
http://localhost:8080
```

All endpoints use the `/v1` path prefix.

## 2. Authentication

The API uses HTTP Basic authentication together with a JWT token for user-specific operations.

### 2.1 HTTP Basic Authentication

HTTP Basic authentication is required to access the API.

The application currently uses the following local credentials:

```text
Username: HoneyApiPlayground
Password: HoneyBasicPassword
```

These credentials are intended for local learning and demonstration purposes only. They must not be used in a production environment.

### 2.2 Authentication Flow

The complete flow is:

1. Send HTTP Basic credentials with the request.
2. Register a user, if necessary.
3. Send the user email and password to `POST /v1/login`.
4. Receive a JWT token in the response.
5. Send the token using the `loginToken` header when accessing user operations.

### 2.3 JWT Token

The token must be sent in the `loginToken` request header:

```http
loginToken: jwt-token-here
```

The following operations require this token:

- `GET /v1/user`
- `PATCH /v1/user`
- `DELETE /v1/user`

The default token expiration time is 600 seconds. This value can be changed in the application configuration.

### 2.4 Security Notes

The default credentials and JWT secret are included to make local setup easier. This project is intended for learning purposes and should not be exposed to the internet without changing the credentials, secret, and database configuration.

## 3. Endpoints

### 3.1 Search Hobbies

```http
GET /v1/hobbies
```

Returns all hobbies available during user registration.

Example response:

```json
{
  "hobbies": [
    "Programming",
    "Watch movies",
    "Watch series"
  ]
}
```

### 3.2 Search Countries

```http
GET /v1/countries
```

Returns all countries available in the application.

Example response:

```json
{
  "countries": [
    "Afghanistan",
    "Albania",
    "Algeria",
    "..."
  ]
}
```

### 3.3 Search Programming Time Options

```http
GET /v1/programming-time-options
```

Returns all programming time options available during user registration.

Example response:

```json
{
  "programmingTimeOptions": [
    "Less than a month",
    "Between 1 and 6 months",
    "Between 6 months and 1 year",
    "..."
  ]
}
```

### 3.4 Login

```http
POST /v1/login
```

Authenticates a registered user and returns a JWT token.

Request body:

```json
{
  "email": "email@domain.com",
  "password": "SomeRandomPassword"
}
```

Example response:

```json
{
  "token": "jwt-token-here"
}
```

### 3.5 Register User

```http
POST /v1/user
```

Creates a new user.

Request body:

```json
{
  "nickName": "testingGuy",
  "name": "Joaquim",
  "email": "email@domain.com",
  "password": "SomeRandomPassword",
  "programmingTime": "Between 6 months and 1 year",
  "bornData": {
    "date": "2022-12-22",
    "country": "Brazil"
  },
  "hobbies": [
    "Programming",
    "Watch movies"
  ]
}
```

Returns `201 Created` when the user is successfully registered.

### 3.6 Search User

```http
GET /v1/user
```

Returns the data of the user identified by the JWT token.

Required header:

```http
loginToken: jwt-token-here
```

Example response:

```json
{
  "nickname": "testingGuy",
  "name": "Joaquim",
  "email": "e***@domain.com",
  "programmingTime": "Between 6 months and 1 year",
  "bornDataDTO": {
    "date": "2022-12-22",
    "country": "Brazil"
  },
  "hobbies": [
    "Programming",
    "Watch movies"
  ]
}
```

### 3.7 Update User

```http
PATCH /v1/user
```

Updates the data of the user identified by the JWT token.

Required header:

```http
loginToken: jwt-token-here
```

Request body:

```json
{
  "nickName": "newNickName",
  "name": "newName",
  "password": "myNewPassword",
  "programmingTime": "More than 2 years",
  "bornData": {
    "date": "2023-01-15",
    "country": "Australia"
  },
  "hobbies": [
    "Programming",
    "Watch series",
    "Ride a bicycle"
  ]
}
```

### 3.8 Delete User

```http
DELETE /v1/user
```

Deletes the user identified by the JWT token.

Required header:

```http
loginToken: jwt-token-here
```

## 4. Main HTTP Status Codes

| Status | Meaning |
|---|---|
| `200 OK` | Request completed successfully |
| `201 Created` | User was successfully created |
| `204 No Content` | User was successfully updated or deleted |
| `400 Bad Request` | Request validation failed or data is already in use |
| `401 Unauthorized` | Credentials or JWT token are invalid |
| `404 Not Found` | Requested resource was not found |

## 5. Collections

Request examples are available in the [Postman and Insomnia collections](../README.md#collections-).

[Back to README](../README.md)
