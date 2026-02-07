# Buystuff

A comprehensive e-commerce app built with Spring Boot 3.5.8 and Java 17. This application provides a complete backend solution for managing products, user accounts, shopping carts, orders, and payment processing for an online retail platform.

## Project Overview (API)

Buystuff API is a production-ready backend service designed to support a full-featured e-commerce platform. The application implements industry-standard practices including layered architecture, JWT-based authentication, role-based access control, and comprehensive data validation.

## Key Features

- **User Authentication & Authorization**: JWT-based token authentication with Spring Security and OAuth2 resource server support
- **Account Management**: User registration, profile management, and address handling
- **Product Catalog**: Complete product management with categories, pricing, and inventory tracking
- **Shopping Cart**: Full shopping cart functionality with cart item management
- **Order Processing**: Order creation, status tracking, and order item management with sales analytics
- **Review System**: Product review and rating functionality
- **Coupon System**: Discount code management and validation
- **Payment Integration**: Payment information storage and processing capabilities
- **Global Exception Handling**: Centralized error handling and validation responses
- **API Documentation**: OpenAPI/Swagger specification for API contracts

## Technology Stack

- **Framework**: Spring Boot 3.5.8
- **Language**: Java 17
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT tokens and OAuth2 Resource Server
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Spring Boot Validation Starter
- **Code Generation**: Lombok for reduced boilerplate
- **Development**: Spring Boot DevTools

## Architecture

The application follows a layered, domain-driven architecture organized by feature:

```
src/main/java/com/buystuff/buystuff_api/
├── controllers/          # REST endpoints and request handling
├── services/            # Business logic layer
├── repositories/        # Data access layer (Spring Data JPA)
├── entities/            # JPA entity classes
├── dto/                 # Data transfer objects organized by domain
├── mappers/             # Entity-to-DTO mapping logic
├── configuration/       # Spring configuration and security setup
├── exceptions/          # Custom exception classes
├── enums/               # Application enumerations
└── abstract_classes/    # Base classes for shared functionality
```

This architecture ensures proper separation of concerns and maintainability across the application.

## Prerequisites

- Java 17 or higher
- Maven 3.6.0 or higher
- PostgreSQL 12 or higher
- Git

## Installation and Setup

### 1. Clone the repository

```bash
git clone <repository-url>
cd buystuff-api
```

### 2. Configure database connection

Update `src/main/resources/application-local.properties` with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/buystuff
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Install dependencies

```bash
mvn clean install
```

### 4. Initialize the database

The application automatically runs `schema.sql` and `data.sql` on startup to set up the database schema and populate initial data.

## Running the Application

### Development mode with hot reload

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### Building and running the JAR

```bash
mvn clean package
java -jar target/buystuff-api-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080` by default.

## API Documentation

The API is documented using OpenAPI 3.0 specification. Access the documentation through:

- **OpenAPI Specification**: `src/main/resources/openapi.yml`
- **Swagger UI**: Available at `http://localhost:8080/swagger-ui.html` (when integrated)
- **API Endpoints**: Postman collection available at `src/main/resources/buystuff.postman_collection`

### Core API Endpoints

**Authentication**
- `POST /api/auth/signup` - User registration
- `POST /api/auth/login` - User login
- `GET /api/auth/me` - Get current user information

**Products**
- `GET /api/products` - List all products
- `GET /api/products/{product_id}` - Get product details
- `POST /api/products` - Create product (admin only)
- `DELETE /api/products/{product_id}` - Delete product (admin only)

**Categories**
- `GET /api/categories` - List all categories

**Shopping Cart**
- `POST /api/cart` - Add item to cart
- `POST /api/cart` - Update cart item

**Orders**
- Complete order lifecycle management with status tracking

**Reviews**
- `POST /api/reviews` - Create product review
- `DELETE /api/reviews/{review_id}` - Delete review

## Database Design

The application uses PostgreSQL with a comprehensive schema that includes:

- **users** - User account information with authentication credentials
- **accounts** - Customer account details and addresses
- **products** - Product catalog with pricing and inventory
- **orders** - Order information with status tracking
- **order_items** - Line items within orders
- **cart_items** - Shopping cart contents
- **reviews** - Product reviews and ratings
- **categories** - Product categorization
- **coupons** - Discount codes
- **payment_info** - Payment method storage
- **sales_info** - Sales analytics and revenue tracking

The schema includes automated triggers for maintaining sales analytics data during order status transitions.

## Key Implementation Details

- **Service Layer**: Interfaces and implementations organized by domain (ProductService, OrderService, etc.)
- **DTO Pattern**: Separate data transfer objects for API requests and responses
- **Exception Handling**: Global exception handler with proper HTTP status codes and meaningful error messages
- **Validation**: Input validation using Spring's validation framework
- **Repository Pattern**: Spring Data JPA repositories for clean data access
- **Mapper Pattern**: Dedicated mapper classes for entity-to-DTO conversion

## Project Structure

- `/src/main/java/` - Application source code
- `/src/main/resources/` - Configuration files and SQL scripts
- `/src/test/java/` - Unit and integration tests
- `/pom.xml` - Maven project configuration and dependencies

## Development Guidelines

The codebase follows standard Java and Spring Boot conventions:

- Proper use of Spring annotations (@Service, @Repository, @RestController)
- Constructor-based dependency injection
- Immutable DTOs for API contracts
- Comprehensive validation on API inputs
- Meaningful exception messages for debugging

## Testing

Unit and integration tests are located in `/src/test/java/`. Run tests with:

```bash
mvn test
```

## Future Enhancements

- Integration testing suite expansion
- API rate limiting and throttling
- Advanced product filtering and search capabilities
- Newsletter and email notification system
- Product recommendation engine
- Analytics and reporting endpoints

## License

This project is provided as-is for educational and commercial purposes.

---

For questions or contributions, please refer to project documentation or contact the development team.
