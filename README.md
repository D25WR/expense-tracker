# 💰 Smart Expense Tracker & Budget Analyzer

A full-stack personal finance application that helps people record daily expenses, categorize spending, and compare actual spend against monthly budgets — solving the very common real-world problem of **"where did all my money go?"**

Built with **Java Spring Boot**, **Spring Security + JWT**, **MySQL**, and **React**.

## 🧩 The Problem

Most people don't track expenses because existing tools are either too complex (accounting software) or too simple (a spreadsheet with no insight). This app gives an individual user a lightweight, secure way to log expenses and instantly see whether they're on track against a monthly budget per category.

## ✨ Features

- 🔐 Secure registration/login with **JWT authentication** and BCrypt password hashing
- 💸 Add, view, and delete expenses with category, note, and date
- 🗂️ Category-based organization (Food, Rent, Travel, etc.)
- 📊 Monthly **budget vs. actual** analysis per category, with over-budget alerts
- 📈 Interactive spending breakdown pie chart (React + Recharts)
- 🌐 REST API secured with stateless JWT, CORS-enabled for a separate frontend

## 🏗️ Architecture

```
React SPA (Axios) ──HTTPS/JWT──▶ Spring Boot REST API ──JPA/Hibernate──▶ MySQL
                                        │
                                Spring Security (JWT filter)
```

**Backend layers:** `controller` → `service` → `repository` → `entity`, with a global exception handler and DTOs to keep the API contract independent of the persistence model.

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot 3, Spring Security, Spring Data JPA |
| Auth | JWT (jjwt), BCrypt |
| Database | MySQL 8 |
| Frontend | React 18, React Router, Axios, Recharts |
| Build | Maven, npm |

## 🚀 Getting Started

### Prerequisites
- Java 17+, Maven, MySQL 8, Node.js 18+

### Backend
```bash
cd backend
cp src/main/resources/application-example.properties src/main/resources/application.properties
# edit application.properties with your MySQL credentials and JWT secret
mvn spring-boot:run
```
API runs on `http://localhost:8080`.

### Frontend
```bash
cd frontend
npm install
npm start
```
App runs on `http://localhost:3000`.

## 📡 Key API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Create an account |
| POST | `/api/auth/login` | Log in, returns JWT |
| GET | `/api/expenses` | List the logged-in user's expenses |
| POST | `/api/expenses` | Add a new expense |
| DELETE | `/api/expenses/{id}` | Delete an expense |
| GET | `/api/categories` | List expense categories |
| GET | `/api/budgets/summary?month=&year=` | Budget vs. actual for a month |

## 🗺️ Roadmap

- [ ] Recurring expenses & reminders
- [ ] CSV export and bank statement import
- [ ] Multi-currency support
- [ ] Dockerized deployment (docker-compose for API + MySQL)

## 👩‍💻 Author

**Divya Waghmare** — [LinkedIn](https://linkedin.com/in/divya-waghmare) · [GitHub](https://github.com/D25WR)

## 📄 License

MIT — see [LICENSE](LICENSE)
