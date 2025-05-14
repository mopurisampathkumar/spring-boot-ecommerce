# 🛒 Spring Boot eCommerce REST API

This is a RESTful eCommerce backend application built with Spring Boot. It provides APIs for managing categories, products, and user-related operations. The application uses an in-memory H2 database and has been tested using Postman.

---

## 🚀 Features

- REST APIs for CRUD operations
- H2 in-memory database for fast development/testing
- Custom Exception Handling
- Data Transfer Objects (DTOs) for clean API contracts
- API testing via Postman
- Layered architecture (Controller, Service, Repository)
- Lombok to reduce boilerplate code

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Lombok
- Maven

---

## 🧱 Project Structure

spring-boot-ecommerce/<br>
├── src/<br>
│ ├── main/<br>
│ │ ├── java/<br>
│ │ │ └── com/sampath/projects/ecommerce/<br>
│ │ │ ├── controller/ # REST controllers<br>
│ │ │ ├── dto/ # Data Transfer Objects<br>
│ │ │ ├── exception/ # Custom exception classes & handlers<br>
│ │ │ ├── model/ # JPA entity classes<br>
│ │ │ ├── repository/ # Spring Data JPA repositories<br>
│ │ │ └── service/ # Interfaces and service implementations<br>
│ │ └── resources/<br>
│ │ └── application.properties # Spring Boot configuration<br>
│ │ 
├── test/ # Unit and integration tests<br>
├── pom.xml # Maven build file with dependencies<br>
└── README.md # Project documentation (you’re here!)<br>


---

## 🧪 API Testing

All APIs were tested using Postman. Examples:

- `GET /api/categories` — Get all categories  
- `POST /api/categories` — Create a new category  
- `PUT /api/categories/{id}` — Update a category  
- `DELETE /api/categories/{id}` — Delete a category  

---

## 💾 H2 Database Configuration

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: *(empty)*

---

## ⚠️ Custom Exception Handling

Global exception handling is implemented using `@ControllerAdvice`. Common custom exceptions include:

- `ResourceNotFoundException`
- `BadRequestException`

These provide clear and consistent error responses.

---

## 📦 DTOs (Data Transfer Objects)

DTOs are used to:

- Avoid exposing entity models directly
- Implement validation using annotations
- Separate internal logic from external API contracts

---

## 🧼 How to Run

Make sure you have Java 17+ and Maven installed.

```bash
git clone https://github.com/your-username/spring-boot-ecommerce.git
cd spring-boot-ecommerce
mvn clean install
mvn spring-boot:run
