# 🔐 Spring Boot Basic Auth API

A clean, minimal authentication service built with **Java Spring Boot** — supporting user registration, login, and validation using DTOs, global exception handling, and password hashing with Spring Security.

---

## 🚀 Features

- User registration with unique email and username validation  
- Secure login with BCrypt password encryption  
- Input validation via DTOs (`@Valid`, `@NotBlank`, `@Email`, etc.)  
- Centralized exception handling (`@RestControllerAdvice`)  
- Database schema via JPA/Hibernate  
- Constructor-based dependency injection (no field injection)  
- Spring Security setup with custom filter chain  
- Postman-ready API endpoints  

---

## 🧱 Tech Stack

- **Java 17+**
- **Spring Boot 3+**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Jakarta Validation (Bean Validation)**
- **MySQL / PostgreSQL (or any JPA-compatible DB)**
- **Lombok**

---

## 📁 Project Structure

```
src/
 └── main/
     ├── java/com/project/auth/
     │   ├── config/               # Security configuration
     │   ├── controller/           # REST controllers (Auth endpoints)
     │   ├── dtos/                 # Request and Response DTOs
     │   ├── entity/               # JPA Entities (User)
     │   ├── exception/            # Custom exceptions + global handler
     │   ├── repository/           # Spring Data JPA repositories
     │   └── service/              # AuthService interface + implementation
     └── resources/
         ├── application.properties
         └── data.sql / schema.sql (optional)
```

---

## ⚙️ Setup & Run

### 1. Clone the repo
```bash
git clone https://github.com/yourusername/springboot-basic-auth.git
cd springboot-basic-auth
```

### 2. Configure your database
In `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build & Run
```bash
./mvnw spring-boot:run
```

Or from an IDE like IntelliJ/Eclipse — run `AuthApplication.java`.

---

## 📬 API Endpoints

### **Register User**
**POST** `/api/auth/register`

Request body:
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "username": "johndoe",
  "email": "john@example.com",
  "password": "Password@123"
}
```

Response:
```json
{
  "username": "johndoe",
  "email": "john@example.com"
}
```

---

### **Login User**
**POST** `/api/auth/login`

Request body:
```json
{
  "username": "johndoe",
  "password": "Password@123"
}
```

Response:
```json
{
  "username": "johndoe",
  "email": "john@example.com"
}
```

---

## ⚡ Validation Rules

| Field | Rule |
|-------|------|
| `firstName`, `lastName` | Must be between 2–50 characters |
| `username` | 4–20 chars, letters/numbers/underscores only |
| `email` | Valid email format |
| `password` | At least 8 chars, includes uppercase, lowercase, number, special char |

---

## 🧩 Exception Handling

All exceptions are handled centrally in `GlobalExceptionHandler`.  
Responses follow a consistent format:

```json
{
  "message": "Email already exists",
  "statusCode": 409,
  "error": "Conflict",
  "path": "/api/auth/register",
  "timestamp": "2025-10-22T13:45:00"
}
```

---

## 🔐 Security Configuration

Defined in `SecurityConfig.java`:
- CSRF disabled for stateless API
- `/api/auth/register` and `/api/auth/login` are public
- All other routes require authentication
- Passwords hashed using `BCryptPasswordEncoder`

---

## 🧠 Design Choices

- **Constructor Injection** over field injection (explicit, testable, immutable)
- **DTO-based validation** (API-level input checks)
- **Entity constraints** only for DB-level integrity
- **Transactional service layer** for atomic operations

---

## 🧪 Testing

You can test the endpoints directly using **Postman**:

1. Import a new collection
2. Add:
   - `POST /api/auth/register`
   - `POST /api/auth/login`
3. Set `Content-Type: application/json`
4. Verify responses and validation errors

---

## 🧾 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Author

**Dhruv** — Software Developer  
Feel free to connect or contribute!
