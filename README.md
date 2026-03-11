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
