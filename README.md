# Task Manager API

This is a simple **RESTful API** built with **Spring Boot** to manage tasks. It supports basic **CRUD operations** for tasks, including the ability to filter tasks by status (e.g., "Open", "In Progress", "Done").

The API is designed to demonstrate **Spring Boot**, **Spring Data JPA**, and an **H2 in-memory database**. This project is ideal for showcasing your Java and Spring knowledge.

---

## Table of Contents

- [Technologies](#technologies)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Database Console](#database-console)
- [Testing](#testing)
- [License](#license)

---

## Technologies

- **Spring Boot** - Framework for building Java-based applications.
- **Spring Data JPA** - Simplifies database access and interaction.
- **H2 Database** - In-memory database for demonstration purposes.
- **Lombok** - Reduces boilerplate code for model classes.
- **Spring Validation** - Provides constraints and validation for request bodies.
- **JUnit** - Used for writing unit tests for service and controller layers.

---

## Features

- Create, read, update, and delete tasks.
- Filter tasks by their status (Open, In Progress, Done).
- Simple validation for required fields.
- In-memory H2 database for easy setup and testing.
- Pagination and sorting for retrieving tasks (through Spring Data JPA).
- Simple and intuitive REST API design.

---

## Getting Started

### Prerequisites

Ensure that you have the following installed:
- **Java 17** or higher
- **Maven** or **Gradle** (Maven is used in this project)
- **IDE** like IntelliJ IDEA, Eclipse, or Visual Studio Code

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/aryala7/taskmanager-api.git
   ```

2. Navigate to the project directory:
   ```bash
   cd taskmanager-api
   ```

3. Build the project with Maven:
   ```bash
   mvn clean install
   ```

### Running the Application

To run the application, simply use the following command:
```bash
mvn spring-boot:run
```

This will start the application on `http://localhost:8080`. You can interact with the API through Postman or any HTTP client.

---

## API Endpoints

### **GET** `/tasks`
Retrieve all tasks.
- **Response:** A list of tasks.

### **GET** `/tasks/status/{status}`
Retrieve tasks by status.
- **Path Variable:**
    - `status`: The status of the task (Open, In Progress, Done).
- **Response:** A list of tasks with the given status.

### **POST** `/tasks`
Create a new task.
- **Request Body:**
  ```json
  {
    "title": "New Task",
    "description": "Task description here",
    "status": "OPEN"
  }
  ```
- **Response:** Created task with its ID.

### **PUT** `/tasks/{id}`
Update an existing task by ID.
- **Path Variable:** `id` - The ID of the task to update.
- **Request Body:**
  ```json
  {
    "title": "Updated Task",
    "description": "Updated task description",
    "status": "IN_PROGRESS"
  }
  ```
- **Response:** The updated task.

### **DELETE** `/tasks/{id}`
Delete a task by ID.
- **Path Variable:** `id` - The ID of the task to delete.
- **Response:** 204 No Content.

---

## Database Console

Since we are using **H2** as the in-memory database, you can access the H2 console to query the database:

1. Open the console at: `http://localhost:8080/h2-console`
2. The default JDBC URL is: `jdbc:h2:mem:taskdb`
3. Username: `sa`
4. Password: `password`

---

## Testing

Unit and integration tests are written using **JUnit**. To run the tests, execute:
```bash
mvn test
```

This will run all tests and display the results.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
