# Employee Management System

## Project Overview

The Employee Management System is a REST API application developed using Spring Boot and MySQL. It allows users to create, retrieve, update, and delete employee records.

The project demonstrates RESTful API development, layered architecture, input validation, database integration, and centralized exception handling.

## Technologies Used

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Jakarta Bean Validation
* Postman
* Eclipse IDE

## Features

* Create a new employee
* Retrieve all employees
* Retrieve an employee by ID
* Update employee details
* Delete an employee
* Validate employee input
* Prevent duplicate email addresses
* Handle employees that do not exist
* Handle invalid and malformed JSON requests
* Return appropriate HTTP status codes and error messages

## Project Structure

```text
com.example.employeemanagement
├── controller
│   └── EmployeeController.java
├── dto
│   ├── EmployeeRequest.java
│   └── EmployeeResponse.java
├── entity
│   └── Employee.java
├── exception
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository
│   └── EmployeeRepository.java
├── service
│   └── EmployeeService.java
└── EmployeeManagementApplication.java
```

## Prerequisites

Install the following before running the project:

* Java Development Kit (JDK) 21
* Maven
* MySQL or a compatible local MySQL server
* Eclipse IDE or another Java IDE
* Postman for API testing

## Database Configuration

Create a database named `employee_management` in MySQL:

```sql
CREATE DATABASE employee_management;
```

Configure the database connection in:

`src/main/resources/application.properties`

Example configuration:

```properties
spring.application.name=EmployeeManagement

spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=root
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

Update the database username and password to match your local MySQL configuration.

**Security note:** Do not commit real database passwords or other secrets to GitHub.

## Running the Application

1. Clone or download the project.
2. Open the project in Eclipse as a Maven project.
3. Make sure the database server is running.
4. Create the `employee_management` database.
5. Verify the database settings in `application.properties`.
6. Allow Maven to download the required dependencies.
7. Run `EmployeeManagementApplication.java` as a Java application.

The application runs at:

`http://localhost:8080`

## API Documentation

All endpoints use the base URL:

`http://localhost:8080/api/employees`

### 1. Create an Employee

* **Method:** POST
* **Endpoint:** `/api/employees`
* **Success status:** `201 Created`

Example request:

```json
{
  "fullName": "Priya Thomas",
  "email": "priya.thomas@example.com",
  "department": "HR",
  "designation": "HR Executive",
  "salary": 32000
}
```

### 2. Retrieve All Employees

* **Method:** GET
* **Endpoint:** `/api/employees`
* **Success status:** `200 OK`

### 3. Retrieve an Employee by ID

* **Method:** GET
* **Endpoint:** `/api/employees/{id}`
* **Success status:** `200 OK`

Example:

`GET /api/employees/1`

### 4. Update an Employee

* **Method:** PUT
* **Endpoint:** `/api/employees/{id}`
* **Success status:** `200 OK`

Example request:

```json
{
  "fullName": "Priya Thomas",
  "email": "priya.thomas@example.com",
  "department": "HR",
  "designation": "HR Manager",
  "salary": 40000
}
```

### 5. Delete an Employee

* **Method:** DELETE
* **Endpoint:** `/api/employees/{id}`
* **Success status:** `204 No Content`

Example:

`DELETE /api/employees/1`

## Postman Collection

The Postman collection is included in the repository as
`Employee Management System API.postman_collection.json`.

Import this file into Postman to test the employee CRUD endpoints.

Before testing, make sure the Spring Boot application and MySQL server are running.

## Validation and Exception Handling

The application validates employee input, including:

* Required employee name, email, department, and designation
* Valid email format
* Salary greater than zero
* Required salary value

A centralized exception handler provides consistent error responses.

| Scenario                        | HTTP Status     |
| ------------------------------- | --------------- |
| Employee created                | 201 Created     |
| Request successful              | 200 OK          |
| Employee deleted                | 204 No Content  |
| Invalid input or malformed JSON | 400 Bad Request |
| Employee not found              | 404 Not Found   |
| Database constraint conflict    | 409 Conflict    |

Duplicate email addresses are rejected with a `400 Bad Request` response.

## Testing

The APIs can be tested using Postman.

The following scenarios have been tested:

* Creating an employee
* Retrieving all employees
* Retrieving an employee by ID
* Updating employee details
* Deleting an employee
* Handling a nonexistent employee ID
* Rejecting invalid employee data
* Rejecting duplicate email addresses
* Handling malformed JSON requests

## Author

Developed as a Spring Boot REST API project demonstrating employee management, database integration, validation, and exception handling.
