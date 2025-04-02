# <span style="color:#3498db">Todo Management API</span>

This is a **Todo Management** REST API built with **Spring Boot**...

## <span style="color:#2ecc71">Features</span>
- **User Authentication**: JWT-based authentication for secure login and registration.
- **Role-based Authorization**: Differentiates user roles (ADMIN, USER) for access control.
- **Todo Management**: Allows creating, updating, completing, and deleting tasks.

## <span style="color:#f39c12">Tech Stack</span>
- **Java 11**
- **Spring Boot 2.x**
- **Spring Security** for authentication and authorization
- **JWT** for token-based authentication
- **H2 Database** (or PostgreSQL if preferred)

## <span style="color:#e74c3c">API Endpoints</span>

### <span style="color:#8e44ad">AuthController</span>

#### <span style="color:#9b59b6">1. Register</span>
```http
POST /api/auth/register

```
- **Request body**:
  ```json
  {
    "username": "user",
    "password": "password",
    "email": "user@example.com"
  }
  ```
- **Response**: 
  - `201 Created`: Registration successful.
  - `400 Bad Request`: Invalid input data.

#### 2. Login
```http
POST /api/auth/login
```
- **Request body**:
  ```json
  {
    "username": "user",
    "password": "password"
  }
  ```
- **Response**:
  - `200 OK`: Successful login, returns a JWT token.
  ```json
  {
    "accessToken": "jwt_token_here"
  }
  ```
  
### TodoController

#### 1. Get All Todos
```http
GET /api/todo/getAllTodo
```
- **Authorization**: Requires **ADMIN** or **USER** role.
- **Response**:
  - `200 OK`: Returns a list of all todos.
  - `401 Unauthorized`: Token is missing or invalid.

#### 2. Get Todo by ID
```http
GET /api/todo/{id}/getByIdTodo
```
- **Path Parameter**: `id` (Todo ID)
- **Authorization**: Requires **ADMIN** or **USER** role.
- **Response**:
  - `200 OK`: Returns the todo item with the given ID.

#### 3. Add Todo
```http
POST /api/todo/addTodo
```
- **Request body**:
  ```json
  {
    "title": "New Todo",
    "description": "Description of the new task"
  }
  ```
- **Authorization**: Requires **ADMIN** or **USER** role.
- **Response**:
  - `201 Created`: Todo item successfully added.

#### 4. Update Todo
```http
PUT /api/todo/{id}/updateTodo
```
- **Path Parameter**: `id` (Todo ID)
- **Request body**:
  ```json
  {
    "title": "Updated Todo",
    "description": "Updated description"
  }
  ```
- **Authorization**: Requires **ADMIN** role.
- **Response**:
  - `200 OK`: Updated todo item.

#### 5. Delete Todo
```http
DELETE /api/todo/{id}/deleteTodo
```
- **Path Parameter**: `id` (Todo ID)
- **Authorization**: Requires **ADMIN** role.
- **Response**:
  - `200 OK`: Todo item successfully deleted.

#### 6. Mark Todo as Completed
```http
PATCH /api/todo/{id}/completed
```
- **Path Parameter**: `id` (Todo ID)
- **Authorization**: Requires **ADMIN** or **USER** role.
- **Response**:
  - `200 OK`: Updated todo item (marked as completed).

#### 7. Mark Todo as Incompleted
```http
PATCH /api/todo/{id}/inCompletedTodo
```
- **Path Parameter**: `id` (Todo ID)
- **Authorization**: Requires **ADMIN** or **USER** role.
- **Response**:
  - `200 OK`: Updated todo item (marked as incomplete).

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/todo-management-api.git
   cd todo-management-api
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will run on `http://localhost:8080`.

## Configuration

### Application Properties

Make sure to set up the following properties in your `application.properties`:

```properties
app.jwt-secret=your-secret-key-here
app.jwt-expiration-milliseconds=604800000
```

## Conclusion

This API is a simple project to demonstrate how to handle JWT-based authentication, role-based authorization, and basic CRUD operations for a Todo application. You can extend the API with more features such as pagination, search, or integration with external services.
