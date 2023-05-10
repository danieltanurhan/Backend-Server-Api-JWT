# Spring Boot Sign-in Demo with MySQL, Spring Security and JWT

- Demo project implementing appropriate flow for User signup and signing as well as post creation and retrival.
- Configured Spring Boot Rest Api Architecture with Spring Security to work with JWT.
- Defined Data Models and services for Authentication and Authorization.
- Interaction configuration for Spring Data JPA with MySQL.

## Run Locally

Clone the project

```bash
  git clone https://github.com/danieltanurhan/Backend-Server-Api-JWT.git
```

Go to the project directory

```bash
  cd Backend-Server-Api-JWT
```

Install dependencies

```bash
  npm install
```

Start the server

```bash
./gradlew bootrun
```


## User API Reference

#### Register user

```http
  POST /api/signup
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**.|
| `password` | `string` | **Required**.|
| `email` | `string` | **Required**.|
| `role` | `set<string>` | **Required**.|


#### Sign in

```http
  POST /api/signin
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username`      | `string` | **Required**. |
| `password`      | `string` | **Required**. |

#### Sign out

```http
  POST /api/signout
```

#### Access Test

```http
  GET /api/test/all
  GET /api/test/user
  GET /api/test/mod
  GET /api/test/admin
```

- Use this to test role access authorization
