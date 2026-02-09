---
description: 'Guidelines for building Spring Boot base applications'
applyTo: '**/*.java, **/*.kt'
---

# Spring Boot Development

## General Instructions

- Make only high-confidence suggestions when reviewing code changes.
- Write maintainable code with comments explaining **why** decisions were made.
- Handle edge cases explicitly and use clear exception handling.
- Mention external libraries and their purpose in comments when used.
- Prefer clarity over cleverness.

---

## Dependency Injection

- Use **constructor injection** for all required dependencies with the use of `@RequiredArgsConstructor` annotation.
- Declare injected fields as `private final`.
- Avoid field injection (`@Autowired` on fields).

---

## Configuration

- Use `application.yml` for all configuration.
- Use **Spring Profiles** (`dev`, `test`, `prod`) for environment-specific behavior.
- Use `@ConfigurationProperties` for type-safe config binding.
- Never commit secrets — load them from environment variables or secret managers.

---

## Code Organization

- Organize packages **by feature/domain**, not by layer.
  - Example: `product`, `order`, `auth`, `review`
- Keep controllers thin:
  - request validation
  - authentication context extraction
- Keep services focused on business logic.
- Keep repositories simple (no business rules).
- Utility classes must be `final` with private constructors.

---

## Controller Layer

- Controllers must **not expose JPA entities** in request or response bodies.
- Use DTOs for all API contracts.
- Validate input using JSR-380 annotations (`@NotNull`, `@Size`, etc.).
- Use `@Valid` and handle validation errors centrally.

---

## Service Layer

- Place business logic in `@Service` classes.
- Services must be **stateless and testable**.
- Inject repositories via constructors.
- Service methods should accept **IDs or DTOs**, not entities unless strictly required.

### Transactions

- Define transaction boundaries at the **service layer**.
- Use `@Transactional` for write operations.
- Use `@Transactional(readOnly = true)` for read-only flows.

---

## Persistence & Repositories

- Always use Spring Data JPA or `NamedParameterJdbcTemplate`.
- Never construct SQL via string concatenation.
- Avoid `FetchType.EAGER` by default.
- Use pagination (`Pageable`) for all list endpoints.
- Write explicit queries for joins to avoid N+1 issues.

---

## DTO & Mapping

- Map entities ↔ DTOs manually or via constructors for now.
- Keep mapping logic close to the service layer.
- Avoid introducing mapping frameworks prematurely.

> Future note: MapStruct can be considered if mapping logic grows large.

---

## Security

- Use Spring Security with JWT for authentication.
- Access the authenticated user via:
  - `@AuthenticationPrincipal`, or
  - `SecurityContextHolder`
- Do **not** inject `HttpServletRequest` into services.
- Enforce role-based access at controller or method level.

---

## Logging

- Use SLF4J only:

  ```java
  private static final Logger logger =
      LoggerFactory.getLogger(MyClass.class);
  ```

- Never use System.out.println.

- Use parameterized logs:

  ```java
  logger.info("User {} placed order {}", userId, orderId);
  ```

## Exception Handling

- Use a global exception handler via `@ControllerAdvice`.

- Define a consistent API error response format.

- Do not leak internal exceptions directly to clients.

## Testing

- Service layer: unit tests for business logic.

- Controller layer: slice tests (@WebMvcTest) for critical APIs.

- Avoid over-testing repositories unless custom queries exist.

## Build and Verification

- Ensure the project builds after every change.

### Maven

  ```shell
  ./mvnw clean package
  ```

### Gradle

  ```shell
  ./gradlew build
  ```

- All tests must pass before merging.
