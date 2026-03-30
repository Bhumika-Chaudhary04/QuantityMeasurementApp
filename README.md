# Quantity Measurement App - Backend

This is the backend service for the **Quantity Measurement App**, built using **Spring Boot**.  
It provides REST APIs for quantity operations such as **addition, subtraction, division, comparison, and unit conversion**, along with **user authentication and authorization** features including **JWT-based login** and **Google OAuth2 login**.

## Features

### Quantity Measurement Features
- Add two quantities
- Subtract two quantities
- Divide quantities
- Compare quantities
- Convert quantities from one unit to another

### Supported Measurement Types
- Length
- Weight
- Temperature
- Volume

### Authentication Features
- User registration
- User login
- JWT token generation and validation
- Logout with token blacklisting
- Google OAuth2 login support
- Protected APIs using Spring Security

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- Hibernate (used internally by JPA)
- H2 Database (in-memory database)
- JWT (JSON Web Token)
- OAuth2 (Google Authentication)
- Maven

## Project Structure

```text
src/main/java/com/apps/quantitymeasurement
│
├── controller          # REST controllers
├── dto                 # Request and response DTOs
├── entity              # JPA entities
├── exception           # Custom exceptions and global handler
├── repository          # Database repositories
├── security            # JWT, OAuth2, security config
├── service             # Service interfaces and implementations
├── units               # Unit enums / measurement logic
├── utils               # Utility classes like converters
└── QuantitymeasurementApplication.java