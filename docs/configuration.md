# Configuration

First of all, thank you for taking a look at this documentation :)

The application supports different Spring profiles for local development and PostgreSQL-based development.

## 1. Available Profiles

### 1.1 `local`

The `local` profile is active by default.

It uses:

- H2 in-memory database
- Predefined data from `data-local.sql`
- H2 Console enabled at `/h2-console`

The database is recreated when the application restarts.

### 1.2 `dev`

The `dev` profile uses PostgreSQL with the following default local connection:

```text
Host: localhost
Port: 5432
Database: honey-api-playground
Username: postgres
```

The database password is configured in `application-dev.yml` and should be changed before using this profile in any shared environment.

## 2. Environment Variables

### 2.1 `APP_PROFILE`

Defines the active Spring profile.

Default value:

```text
local
```

Example:

```bash
APP_PROFILE=dev
```

### 2.2 `JWT_SECRET`

Defines the secret used to sign JWT tokens.

The project contains a default value for local learning purposes. A custom secret should be provided in any environment that is not strictly local.

Example:

```bash
JWT_SECRET=my-custom-secret
```

## 3. H2 Console

When using the `local` profile, the application uses an H2 in-memory database. The H2 Console is enabled to make it easier to inspect the database during local development.

The application must be running before accessing the H2 Console.

After the application has started, open the following URL in your browser:

```text
http://localhost:8080/h2-console
```

In the H2 Console login screen, fill in the connection fields as follows:

| Field | Value |
|---|---|
| Driver Class | `org.h2.Driver` |
| JDBC URL | `jdbc:h2:mem:testdb` |
| User Name | `sa` |
| Password | Leave empty |

Then click **Connect**. The database schema and the predefined local data will be available for inspection while the application is running.

Because this is an in-memory database, its data is lost whenever the application stops or restarts. The tables and predefined data are recreated when the application starts again.

## 4. Security Notes

The default passwords and secrets in this project are intended for local development only. They should be replaced with environment-specific values before sharing or deploying the application.

[Back to README](../README.md)
