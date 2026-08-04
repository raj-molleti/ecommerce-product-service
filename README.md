# Ecommerce Product Catalog Service

Spring Boot + Spring Data MongoDB REST service exposing **Product** and **Category** management,
documented via Swagger / OpenAPI 3 (springdoc).

## Tech Stack
- Java 17
- Spring Boot 3.3.x
- Spring Data MongoDB (repository abstraction, JPA-style)
- springdoc-openapi (Swagger UI)
- Bean Validation (jakarta.validation)
- Lombok

## Project Layout
```
src/main/java/com/nisum/ecommerce
 ├── config/SwaggerConfig.java        # OpenAPI bean
 ├── entity/Product.java              # @Document Product
 ├── entity/Category.java             # @Document Category
 ├── dto/ProductDTO.java              # request/response payload + validation
 ├── dto/CategoryDTO.java
 ├── repository/ProductRepository.java
 ├── repository/CategoryRepository.java
 ├── service/ProductService(.java/Impl.java)
 ├── service/CategoryService(.java/Impl.java)
 ├── controller/ProductController.java
 ├── controller/CategoryController.java
 └── exception/                       # ResourceNotFoundException, DuplicateResourceException,
                                       # GlobalExceptionHandler (@RestControllerAdvice)
```

## Prerequisites
- JDK 17+
- Maven 3.9+
- A running MongoDB instance (local `mongod` on `27017`, Docker, Atlas, or Azure Cosmos DB Mongo API)

Quick local Mongo via Docker:
```bash
docker run -d --name mongo -p 27017:27017 mongo:7
```

Connection is configured in `src/main/resources/application.yml`:
```yaml
spring.data.mongodb.uri: mongodb://localhost:27017/ecommerce_catalog
```

## Build & Run
```bash
mvn clean install
mvn spring-boot:run
```
The service starts on **http://localhost:8080**.

## Swagger / OpenAPI
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## REST Endpoints

### Category
| Method | Path                          | Description        |
|--------|-------------------------------|---------------------|
| POST   | /api/v1/categories             | Save a category     |
| GET    | /api/v1/categories             | Get all categories  |
| GET    | /api/v1/categories/{id}        | Get category by id  |
| DELETE | /api/v1/categories/{id}        | Delete category     |

Sample create payload:
```json
{
  "categoryName": "Mobile Phones",
  "parentCategoryId": null
}
```

### Product
| Method | Path                       | Description       |
|--------|-----------------------------|--------------------|
| POST   | /api/v1/products             | Save a product     |
| GET    | /api/v1/products             | Get all products   |
| GET    | /api/v1/products/{id}        | Get product by id  |
| DELETE | /api/v1/products/{id}        | Delete product     |

Sample create payload:
```json
{
  "productSku": "SKU-TSHIRT-BLU-M",
  "productName": "Men's Cotton Crew-Neck T-Shirt",
  "productPrice": 499.00,
  "productShortName": "Cotton T-Shirt",
  "productDescription": "100% cotton, regular fit, machine washable.",
  "deliveryTimeSpan": "3-5 business days",
  "categoryId": "<existing categoryId from POST /categories response>",
  "productImageUrl": "https://cdn.example.com/images/products/tshirt-blue.jpg"
}
```
Note: `productId` and `createdDate` are server-generated; the API rejects a `categoryId`
that doesn't exist and rejects a duplicate `productSku` (400/404/409 as appropriate — see Swagger).

## Design notes
- `categoryId` on `Product` is stored as a plain reference field (not `@DBRef`) to avoid
  eager-fetch cost on the high-traffic `GET /products` (getAll) path — consistent with
  read-heavy catalog access patterns.
- `GlobalExceptionHandler` centralizes 400/404/409/500 handling into a consistent
  `ErrorResponse` shape across both controllers.
- Unique indexes: `productSku` (Product), and a name-uniqueness check on `Category`
  enforced at the service layer.
- Layered architecture (Controller → Service → Repository) with DTOs isolating the
  wire contract from the persistence model, so the Mongo document shape can evolve
  independently of the API contract.

## Possible extensions
- Pagination (`Pageable`) on `getAll` endpoints for large catalogs
- `@DBRef` or aggregation `$lookup` to resolve nested category details in product responses
- Optimistic locking (`@Version`) on `Product` for concurrent update safety
- Spring Security / OAuth2 resource server for endpoint protection
