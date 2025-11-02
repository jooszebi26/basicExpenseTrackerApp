# 💰 Expense Manager App — Spring Boot REST API

A modern **expense tracking application** built with **Spring Boot**, providing a secure and modular backend for managing user expenses and categories.  
This project demonstrates a clean, real-world **REST API architecture** using best practices such as DTO mapping, validation, and JWT authentication.

---

## 🚀 Features

✅ **User Authentication & Registration**
- Email + Password based login
- Password hashing with `BCryptPasswordEncoder`
- Stateless authentication via **JWT (JSON Web Token)**

✅ **Expense Management**
- CRUD operations for user expenses
- Expenses linked to categories and users
- DTO-based request validation with Jakarta Bean Validation (`@NotBlank`, `@PositiveOrZero`, `@Email`, etc.)

✅ **Security & Error Handling**
- `Spring Security` with JWT filter integration
- Global exception handling (`@RestControllerAdvice`)
- In-memory H2 database for development

---
