# 🛡️ Basic Auth API (Spring Boot)

A simple authentication API built with **Java Spring Boot**.  
It includes user registration, login, DTO-based request handling, centralized exception handling, and Postman-ready endpoints.

---

## 🚀 Features

- **User Registration** – Create a new account with username, email, and password.
- **User Login** – Authenticate using credentials and receive a token (or session).
- **DTOs for Requests/Responses** – Keeps the API payloads clean and decoupled from entity models.
- **Global Exception Handling** – Centralized error responses for all controllers.
- **Validation** – Uses `@Valid` and constraint annotations on DTOs.
- **Postman Compatible** – Importable collection for quick testing.

---

## 🧰 Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Web**
- **Spring Data JPA / Hibernate**
- **PostgreSQL** (configurable)
- **Jakarta Validation**
- **Lombok**
- **Postman** for testing APIs

---

## ⚙️ Project Structure
src/main/java/com/project/auth/
│
├── controller/
│ └── AuthController.java
│
├── dto/
│ ├── RegisterRequest.java
│ ├── LoginRequest.java
│ └── AuthResponse.java
│
├── entity/
│ └── User.java
│
├── exception/
│ ├── GlobalExceptionHandler.java
│ ├── ErrorResponse.java
│ ├── UsernameAlreadyExistsException.java
│ └── EmailAlreadyExistsException.java
│
├── repository/
│ └── UserRepository.java
│
├── service/
│ └── AuthService.java
│
└── AuthApplication.java

---

## 🧠 Notes

- **Passwords should always be encoded (BCryptPasswordEncoder recommended).**
- **DTOs keep your entities clean and protect internal model structure.**
- **Use proper exception handling for predictable, debuggable APIs.**

## 🧑‍💻 Author
Dhruv
Software Developer — building backend systems with clarity and purpose.

