# Dependencies — Smart Expense Tracker & Budget Analyzer

## Backend (Maven — `backend/pom.xml`)

| Dependency | Version | Purpose |
|---|---|---|
| spring-boot-starter-parent | 3.2.5 | Base Spring Boot parent POM (manages versions) |
| spring-boot-starter-web | (managed) | REST controllers, embedded Tomcat |
| spring-boot-starter-data-jpa | (managed) | Hibernate/JPA for MySQL persistence |
| spring-boot-starter-security | (managed) | Auth framework, password encoding, filter chain |
| spring-boot-starter-validation | (managed) | `@Valid` / Bean Validation on request DTOs |
| mysql-connector-j | (managed) | JDBC driver for MySQL |
| jjwt-api / jjwt-impl / jjwt-jackson | 0.11.5 | JWT creation & parsing |
| lombok | (managed) | Boilerplate reduction (`@Data`, `@Builder`, etc.) |
| spring-boot-starter-test | (managed) | JUnit 5 + Spring test utilities |

Java version: **17**. Build tool: **Maven** (`mvn spring-boot:run`).

## Frontend (npm — `frontend/package.json`)

| Dependency | Version | Purpose |
|---|---|---|
| react / react-dom | ^18.2.0 | UI library |
| react-router-dom | ^6.23.0 | Client-side routing (login/register/dashboard) |
| axios | ^1.6.8 | HTTP client with JWT interceptor |
| recharts | ^2.12.7 | Spending breakdown pie chart |
| react-scripts | 5.0.1 | CRA build tooling |

Run with `npm install && npm start`.
