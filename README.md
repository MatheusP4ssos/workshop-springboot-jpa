# 🛍️ E-commerce API

A RESTful API for an e-commerce platform built with Spring Boot. This API handles products, orders, users, and payments.

## 💻 Technologies

### Core
- ☕ Java 17
- 🌱 Spring Boot 3.5.0
- 📊 Spring Data JPA 3.5.0
### Database
- 🗄️ H2 Database
### Tools
- 🔧 Maven 3.9.0
- 📝 Spring Doc OpenAPI UI 2.1.0

# 🗂️ Domain model
![Modelo de Domínio](images/Domain%20model.PNG)
# 🗂️ Domain instance
![Instância de Domínio](images/Domain%20Instance.PNG)

## 🛣️ API Endpoints

### 👥 Users
| Método | Endpoint | Descrição         | Status Codes |
|--------|----------|-------------------|--------------|
| GET | `/users` | List all users    | 200, 500 |
| GET | `/users/{id}` | Get user by ID    | 200, 404, 500 |
| POST | `/users` | Create a new use | 201, 400, 500 |
| PUT | `/users/{id}` | 	Update a user  | 200, 404, 500 |
| DELETE | `/users/{id}` | Delete a user    | 204, 404, 500 |

### Example request to create a user:

curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name": "John Doe", "email": "john@example.com", "phone": "1234567890"}'

### 📑 Categories
| Method | Endpoint           | Description         | Status Codes  |
| ------ | ------------------ | ------------------- | ------------- |
| GET    | `/categories`      | List all categories | 200, 500      |
| GET    | `/categories/{id}` | Get category by ID  | 200, 404, 500 |


## 🗄️ Database Configuration
Development Database (H2)
Access the H2 Console at: http://localhost:8080/h2-console

application.properties
# Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa

spring.datasource.password=

# H2 Console
spring.h2.console.enabled=true

spring.h2.console.path=/h2-console

# JPA Configuration
spring.jpa.show-sql=true

spring.jpa.hibernate.ddl-auto=create-drop

spring.jpa.defer-datasource-initialization=true



## ⭐ Features

- 👥 User Management
- 📑 Product Categories
- 🛒 Order Processing
- ⚠️ Exception Handling
- ✅ Data Validation
- 🔒 Security Implementation
- 📝 Complete Documentation

## 🚀 Getting Started

### Prerequisites
- ☕ Java 17 or higher
- 🔧 Maven
- 📝 Your favorite IDE (We recommend IntelliJ IDEA)

### 🔨 Installation

1. Clone the repository:

   bash git clone [https://github.com/your-username/ecommerce-api.git](https://github.com/your-username/ecommerce-api.git)


2. Navigate to project directory:

   bash cd ecommerce-api


3. Build the project:

   bash mvn clean install


4. Run the application:

   bash mvn spring-boot:run

The API will be available at `http://localhost:8080`

## 👨‍💻 Development

### Project Structure
src/ ├── main/ │ ├── java/ │ │ └── com/matheus/ecommerce_api/ │ │ ├── config/ │ │ ├── controllers/ │ │ ├── entities/ │ │ ├── repositories/ │ │ ├── services/ │ │ └── ECommerceApiApplication.java │ └── resources/ │ └── application.properties └── test/ └── java/ └── com/matheus/ecommerce_api/ └── ECommerceApiApplicationTests.java

### Architecture
- 🎮 Controllers: Handle HTTP requests and responses
- ⚙️ Services: Implement business logic
- 💾 Repositories: Handle data persistence
- 📦 Entities: Domain models
- ⚡ DTOs: Data Transfer Objects

## ⚠️ Error Handling

The API implements a global exception handler that covers:
- 🔍 Resource Not Found Exception
- 🔒 Database Exception
- ❌ Validation Exception
- 🔄 Concurrent Modification Exception

## 🔧 Additional Configuration

### Application Properties
# Server Configuration
server.error.include-message=always
server.error.include-stacktrace=never
spring.mvc.pathmatch.matching-strategy=ant_path_matcher

# JPA Additional Settings
spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.format_sql=true


## 🧪 Testing

Run tests using:
mvn test

### Test Configuration
The project uses a separate application-test.properties file for testing configuration.

## 📈 Future Improvements

- 🔐 Implement JWT Authentication
- 📊 Add Swagger Documentation
- 🔄 Implement Caching
- 🐳 Docker Integration
- 📊 Monitoring and Metrics

### 📚 Educational Disclaimer

This project was developed for learning purposes as part of the course **"Java COMPLETO - Object-Oriented Programming + Projects"** by **Nélio Alves**.  
It is not an official product or commercial application.

## 👤 Author
Matheus Holanda Passos

## 📞 Support

If you have any questions or need help, feel free to:
- 📧 Open an issue
- 🌟 Star the project
- 🔨 Submit a pull request

## 📊 Project Status

🟢 Active Development

---
⭐ Don't forget to star this project if you found it helpful!
