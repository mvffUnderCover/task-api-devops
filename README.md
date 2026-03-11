# Task Manager API

Backend REST API for the **Task Manager** application.
Built with **Spring Boot**, **PostgreSQL**, and **Docker**.

---

## 🚀 Features

* Create tasks
* List all tasks
* Update task status
* Cancel tasks
* Delete tasks
* REST API architecture
* Docker containerization

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* Docker

---

## 📂 Project Structure

```
task-api
│
├── controller
│   └── TaskController
│
├── service
│   └── TaskService
│
├── repository
│   └── TaskRepository
│
├── model
│   ├── Task
│   └── TaskStatus
│
└── dto
    └── StatusUpdateRequest
```

---

## ⚙️ Configuration

Application configuration is located in:

```
src/main/resources/application.properties
```

Example:

```
server.port=9000

spring.datasource.url=jdbc:postgresql://postgres-db:5432/taskdb
spring.datasource.username=postgres
spring.datasource.password=matar

spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Run locally

### 1️⃣ Clone repository

```
git clone https://github.com/yourusername/task-manager.git
```

### 2️⃣ Navigate to backend

```
cd task-api
```

### 3️⃣ Run application

```
mvn spring-boot:run
```

The API will start on:

```
http://localhost:9000
```

---

## 🐳 Run with Docker

Build the image:

```
docker build -t task-api .
```

Run the container:

```
docker run -p 9000:9000 task-api
```

---

## 📡 API Endpoints

### Get all tasks

```
GET /api/tasks
```

### Create a task

```
POST /api/tasks
```

### Delete a task

```
DELETE /api/tasks/{id}
```

### Cancel a task

```
PUT /api/tasks/{id}/cancel
```

### Update task status

```
PUT /api/tasks/{id}/status
```

Example request body:

```json
{
  "status": "DONE"
}
```

---

## 🧪 Example response

```json
{
  "id": 1,
  "title": "Develop Backend",
  "description": "Implement API",
  "status": "IN_PROGRESS"
}
```

---

## 🐳 Docker Compose

The backend is designed to work with:

* PostgreSQL
* Frontend React

Using:

```
docker-compose up --build
```

---

## 📜 License

MIT License
