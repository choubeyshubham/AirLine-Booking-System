# ✈️ Airline Booking Platform (Java Microservices)

> A production-grade Airline Booking & Reservation Platform built with Java Spring Boot Microservices, designed to simulate a mini Global Distribution System (GDS).

## 📖 About the Project

This project goes beyond simple CRUD applications to demonstrate how real backend systems are designed and built. It is a distributed backend system featuring real business workflows, production-grade architecture, event-driven communication, caching strategies, security, and scalable service interactions. 

By exploring this repository, you will see not only *how* to build microservices, but *why* modern systems are designed this way. It is perfect for understanding domain-driven thinking, large-scale project structure, and interview-level system design concepts.

## 🏗️ System Architecture & Design

The platform utilizes a **Microservices Architecture** with a **Database per Service** pattern. 

* **Event-Driven Systems:** Utilizes Apache Kafka for asynchronous workflows, publishing, and consuming events.
* **Saga Pattern:** Implements distributed transactions and handles failure recovery with compensation events for booking flows.
* **API Gateway & Service Discovery:** Manages routing and dynamic service registration using Spring Cloud Gateway and Netflix Eureka.
* **Distributed Resilience:** Incorporates fault tolerance, circuit breakers (Resilience4j), and retry mechanisms.

## 💻 Tech Stack

### Backend Core
* **Java**
* **Spring Boot** / Spring Cloud / Spring Data JPA
* **Netflix Eureka** (Service Discovery)
* **Spring Cloud Gateway** (API Gateway)
* **OpenFeign** (Service-to-Service Communication)
* **Resilience4j** (Circuit Breakers)

### Data & Messaging
* **MySQL** (Database per service)
* **Apache Kafka** (Event-driven communication)
* **Redis** (Multi-layer caching & Token revocation)

### Security
* **Spring Security**
* **JWT Authentication**
* **Role-Based Access Control (RBAC)**

### DevOps & Deployment
* **Docker** & **Docker Compose** (Containerization & Orchestration)
* **Google Jib**
* **Spring Boot Actuator** (Monitoring)

### Third-Party Integrations
* **Razorpay** (Secure payments)
* **Email Notifications**

## ✨ Key Features

### 🧍 Passenger Features
* Advanced flight search engine
* Complete booking and reservation flow
* Secure payment integration
* E-ticket generation
* Automated email confirmations

### 🛠️ Airline Admin Features
* Flight scheduling and route management
* Fare pricing system management
* Fleet management
* Dynamic seat inventory and availability management

### ⚙️ System Features
* API Gateway routing
* Kafka event streaming for decoupling services
* JWT-based security with Redis blacklisting
* Circuit breakers for system resilience
* Fully dockerized multi-service deployment

## 🎯 Learning Outcomes & Career Value

This codebase serves as a blueprint for mastering:
1.  **Distributed System Design:** Splitting domains into independent services and handling inter-service communication safely.
2.  **Advanced Kafka:** Building asynchronous workflows, booking sagas, and handling compensation logic.
3.  **Performance Optimization:** Implementing Redis multi-layer caching and invalidation techniques.
4.  **Real Domain Modeling:** Modeling flights, schedules, complex fare rules
