---

# 📰 Online Content Publication Platform

A scalable microservices-based platform that connects content writers and readers through category-based content distribution.

---

## 🚀 Tech Stack

* **Backend:** Spring Boot, PostgreSQL (AWS RDS), Apache Kafka
* **Frontend:** React.js (with TailwindCSS)
* **Others:** Docker, AWS API Gateway

---

## 📖 1. Project Overview

This project is a **scalable online content publication platform**, built using **microservice architecture**. It connects content writers with readers based on their preferred content categories.

---

## ✅ 2. Functional Requirements

* 🔐 User sign-up and sign-in using JWT
* 👤 Writer profile creation
* ✍️ Content creation, update, and deletion (CRUD)
* 🔔 Real-time user notifications for subscribed categories (via Kafka)
* 📚 Content listing with pagination (20 per page)
* 🌐 Web client for authentication and content browsing

---

## 🗃️ 3. Database Design

> 💡 **Note:** SQL scripts (`CREATE TABLE`) for each microservice are attached in the project/email.

### ERD Diagrams:

* **User Service:**
  Handles user registration, login, and profile management.

* **Content Service:**
  Manages content creation, updates, deletions, categories, and comments.

* **Notification Service:**
  Manages category subscriptions and sends notifications for content changes.

---

## 📊 4. Use Case Diagram

### 👨‍💻 Reader:

* Sign up / Log in
* Subscribe to categories
* Browse and read content
* Comment on posts

### ✍️ Writer:

* Sign up / Log in
* Complete profile to become a writer
* Publish / Update / Delete content
* Subscribe to categories
* Read and comment on content

---

## 🧩 5. Spring Boot Microservices

Each service follows the **"Database per Service"** pattern.

| Service                  | Description                                           | Database                  |
| ------------------------ | ----------------------------------------------------- | ------------------------- |
| **User Service**         | Authenticates users and manages writer profiles       | `user_service_db`         |
| **Content Service**      | Handles content CRUD, categories, and comments        | `content_service_db`      |
| **Notification Service** | Manages subscriptions and sends content notifications | `notification_service_db` |

---

## 🎯 6. Design Patterns Used

* **Builder Pattern** – for flexible object construction
* **Observer Pattern** – via Kafka for user notifications
* **DTO Pattern** – clean API request/response handling

---

## 🐳 7. Docker & Deployment

### 🔧 Dockerized Microservices

* Dockerfiles for each service
* `docker-compose.yml` for local development

### 🚀 Deployment

* Images pushed to **Docker Hub**
* Services deployed on **AWS EC2**
* API Gateway setup using **AWS API Gateway**
* PostgreSQL hosted via **AWS RDS**

---

## 💻 8. React Web Application
### Git hub repo for FE: https://github.com/Vipisanan/content-publication-fe
Built with **React.js + TailwindCSS**

### ✨ Features:

* User sign-up and sign-in UI
* Category-based content browsing
* Pagination (20 items per page)
* Responsive and clean UI

---
