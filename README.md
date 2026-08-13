<div align="center">

# Shop @ISS

### Full-Stack E-Commerce Shopping Cart Application

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-19-61DAFB?logo=react&logoColor=black)](https://react.dev/)
[![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Bootstrap](https://img.shields.io/badge/Bootstrap-5.3-7952B3?logo=bootstrap&logoColor=white)](https://getbootstrap.com/)

A full stack shopping cart application for browsing products, saving favourites, managing a cart, checking out, and viewing past orders. The project was originally built and tested on localhost with a local MySQL database. It was later packaged with Docker and deployed to Railway with a Railway managed MySQL database. That deployment is no longer active, so this repository does not provide a live application link.

[Features](#features) · [Tech Stack](#tech-stack) · [Screenshots](#screenshots) · [Deployment History](#deployment-history) · [Getting Started](#getting-started) · [API Reference](#api-reference) · [Database Schema](#database-schema) · [My Contribution](#my-contribution--favouriteswishlist-feature)

</div>

---

## Screenshots

### Local Development

These screenshots were captured from the original localhost setup during development and testing.

<table>
  <tr>
    <td align="center"><strong>Product Catalogue</strong></td>
    <td align="center"><strong>Product Details & Reviews</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="products" src="https://github.com/user-attachments/assets/07b36a52-08f4-4795-8c66-2618be6cbcaf" /></td>
    <td><img width="100%" alt="detailed products" src="https://github.com/user-attachments/assets/580a99ed-7dec-4d31-a150-2fd7338a34e6" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Favourites / Wishlist</strong></td>
    <td align="center"><strong>Shopping Cart</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="favs" src="https://github.com/user-attachments/assets/12fc91c3-0624-4d41-be9e-44c6367cf84b" /></td>
    <td><img width="100%" alt="shopping cart" src="https://github.com/user-attachments/assets/abefaff8-dd7f-4c29-a372-98b52e323884" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Login</strong></td>
    <td align="center"><strong>Checkout & Payment</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="login" src="https://github.com/user-attachments/assets/91e12dbd-8692-4d5b-b9c0-37e8d1c8cbd6" /></td>
    <td><img width="100%" alt="payment" src="https://github.com/user-attachments/assets/86de5ef8-e199-4ef7-be5c-ad5af7c3c964" /></td>
  </tr>
</table>

### Former Railway Deployment

After the localhost version was working, I deployed the application to **Railway** as a public demonstration. These screenshots show that former deployment. The Railway service is no longer running.

For that deployment, Railway built the root `Dockerfile`. The Docker build compiled the React frontend, copied it into the Spring Boot static resources, packaged the backend as a JAR, and ran both as one container. The application connected to a Railway managed MySQL service through environment variables. MySQL therefore applies to both stages of the project: a local MySQL instance during development and Railway MySQL during the former hosted deployment.

<table>
  <tr>
    <td align="center"><strong>Railway Home / Product Catalogue</strong></td>
    <td align="center"><strong>Product Details</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="Railway deployed homepage" src="docs/images/Homepage.png" /></td>
    <td><img width="100%" alt="Railway deployed product details" src="docs/images/productdetails.png" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Favourites / Wishlist</strong></td>
    <td align="center"><strong>Shopping Cart</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="Railway deployed favourites page" src="docs/images/favourites.png" /></td>
    <td><img width="100%" alt="Railway deployed cart page" src="docs/images/cart.png" /></td>
  </tr>
  <tr>
    <td align="center"><strong>Checkout</strong></td>
    <td align="center"><strong>Account Profile</strong></td>
  </tr>
  <tr>
    <td><img width="100%" alt="Railway deployed checkout page" src="docs/images/checkout.png" /></td>
    <td><img width="100%" alt="Railway deployed account profile page" src="docs/images/profile.png" /></td>
  </tr>
</table>

---

## Features

| Category | Capabilities |
|----------|-------------|
| **Product Catalogue** | Browse, keyword search, category filtering, multi-criteria sorting (name / price / rating), server-side pagination (10/page) |
| **Shopping Cart** | Add/remove items, quantity adjustment, automatic discount calculation, selective checkout |
| **Favourites / Wishlist** | Toggle favourite, view wishlist, bulk clear, post-login resume for unauthenticated users |
| **Orders & Payments** | Checkout flow, credit card entry, order confirmation, purchase history, per-item refund processing |
| **Reviews & Ratings** | Star ratings, written reviews (one per product per order), aggregated average rating |
| **User Accounts** | Registration, session-based authentication, profile management, password recovery |
| **Responsive UI** | Mobile, tablet, and desktop layouts powered by Bootstrap 5.3 |

---

## Tech Stack

| Layer | Technologies |
|-------|-------------|
| **Backend** | Java 17, Spring Boot 3.5.6, Spring MVC, Spring Data JPA (Hibernate), Thymeleaf, Maven |
| **Frontend** | React 19, React Router 7, React Bootstrap, Axios, Bootstrap Icons |
| **Database** | MySQL 8 locally, Railway managed MySQL for the former deployment, Spring Session JDBC |
| **Deployment** | Docker, Railway (historical deployment), GitHub Actions for tests and image builds |
| **Dev & Test Tools** | Lombok, Spring Boot DevTools, H2 in-memory database for tests |

---

## Deployment History

The project went through two operating environments:

| Stage | Application runtime | Database | Status |
|-------|---------------------|----------|--------|
| **Original development** | Spring Boot and React development servers on localhost | Local MySQL 8 database named `tst` | Can still be reproduced locally |
| **Later deployment** | A single Docker container on Railway containing the React production build and Spring Boot JAR | Railway managed MySQL | No longer active |

The checked-in `Dockerfile` and `railway.json` preserve the former deployment configuration. At runtime, `application.properties` first accepts standard `SPRING_DATASOURCE_*` variables, then Railway style `MYSQL*` variables, and finally falls back to the localhost MySQL defaults. H2 is configured only under `src/test/resources` and is used for automated tests, not as the development or Railway database.

The historical setup steps are retained in [docs/railway-deployment.md](docs/railway-deployment.md) as deployment documentation. They do not indicate that a public instance is currently available.

---

## Architecture

The application follows a **layered MVC + service architecture** with clear separation of concerns:

```
                    ┌─────────────────────────────────────────────┐
                    │              Client (Browser)               │
                    └──────────────────┬──────────────────────────┘
                                       │
                    ┌──────────────────▼──────────────────────────┐
                    │         Thymeleaf / React Frontend          │
                    └──────────────────┬──────────────────────────┘
                                       │ HTTP
         ┌─────────────────────────────▼───────────────────────────────┐
         │                    Controller Layer (9)                     │
         │  ProductController · FavouritesController · OrdersController│
         │  ShoppingCartDetailController · ReviewController · ...      │
         └─────────────────────────────┬───────────────────────────────┘
                                       │
         ┌─────────────────────────────▼───────────────────────────────┐
         │                     Service Layer (9)                       │
         │  Interface-driven design (FavouriteService → Impl)          │
         │  @Transactional business logic                              │
         └─────────────────────────────┬───────────────────────────────┘
                                       │
         ┌─────────────────────────────▼───────────────────────────────┐
         │                   Repository Layer (9)                      │
         │  Spring Data JPA · Custom JPQL queries · Composite keys     │
         └─────────────────────────────┬───────────────────────────────┘
                                       │
                    ┌──────────────────▼───────────────────────────┐
                    │     MySQL 8 (local or Railway managed)      │
                    │          8 tables · 4 composite keys         │
                    └──────────────────────────────────────────────┘
```

### Project Structure

```
Shopping-Cart-Application/
├── pom.xml
├── src/main/java/com/Assignment/shopping_carts/
│   ├── Config/             # CORS, WebMvc configuration
│   ├── Controller/         # 9 controllers (MVC + REST)
│   ├── DTO/                # Data transfer objects
│   ├── Interceptor/        # Request logging
│   ├── InterfaceMethods/   # Service interfaces
│   ├── Model/              # 8 JPA entities + 4 composite keys
│   ├── Repository/         # Spring Data JPA repositories
│   └── Service/            # Service implementations
├── src/main/resources/
│   ├── application.properties
│   ├── templates/          # 11 Thymeleaf HTML templates
│   └── static/             # CSS, JS, images
└── shoppingcartfrontend/   # React SPA
    └── src/
        ├── components/     # Header, NavBar, Sidebar
        ├── pages/          # AccountInfo, PurchaseHistory, Register
        └── css/            # Frontend stylesheets
```

---

## Database Schema

8 JPA entities mapped to MySQL tables. Junction tables use `@IdClass` composite primary keys.

### Entity-Relationship Diagram

```
┌──────────────┐        ┌───────────────────────┐        ┌──────────────┐
│   Category   │ 1────* │       Product         │ *────1 │   Customer   │
├──────────────┤        ├───────────────────────┤        ├──────────────┤
│ categoryId PK│        │ productId PK          │        │ customerId PK│
│ name         │        │ productName NOT NULL  │        │ fullName     │
└──────────────┘        │ description (500)     │        │ userName     │
                        │ imageUrl              │        │ email        │
                        │ discount [0–1]        │        │ password     │
                        │ unitPrice [≥0]        │        │ address      │
                        │ averageRating         │        └──────┬───────┘
                        │ category_id FK        │               │
                        └──────┬────────────────┘               │
                               │                                │
             ┌─────────────────┼────────────────────────────────┤
             │                 │                                │
     ┌───────▼─────────┐ ┌─────▼────────────┐  ┌──────────────────▼─┐
     │  Favourites     │ │ ShoppingCart     │  │      Orders        │
     │  (junction)     │ │ Detail (junction)│  ├────────────────────┤
     ├──────────────── ┤ ├──────────────────┤  │ orderId PK         │
     │ productId PK/FK │ │ productId PK/FK  │  │ customerId FK      │
     │ customerId PK/FK│ │ customerId PK/FK │  │ purchaseDate       │
     └─────────────────┘ │ quantity         │  │ unitAmount         │
                         └──────────────────┘  │ status             │
                                               └────────┬───────────┘
                                                     │
                                        ┌────────────┼────────────┐
                                        │                         │
                                ┌───────▼────────┐      ┌────────▼────────┐
                                │  OrderDetail   │      │     Review      │
                                │  (junction)    │      │   (junction)    │
                                ├────────────────┤      ├─────────────────┤
                                │ orderId PK/FK  │      │ productId PK/FK │
                                │ productId PK/FK│      │ customerId PK/FK│
                                │ quantity       │      │ orderId PK/FK   │
                                │ isRefunded     │      │ rating          │
                                └────────────────┘      │ description     │
                                                        └─────────────────┘
```

### Table Definitions

<details>
<summary><strong>category</strong></summary>

| Column | Type | Constraints |
|--------|------|-------------|
| category_id | INT | PK, AUTO_INCREMENT |
| name | VARCHAR | |

</details>

<details>
<summary><strong>customer</strong></summary>

| Column | Type | Constraints |
|--------|------|-------------|
| customer_id | INT | PK, AUTO_INCREMENT |
| full_name | VARCHAR | |
| user_name | VARCHAR | |
| email | VARCHAR | |
| password | VARCHAR | |
| address | VARCHAR | |

</details>

<details>
<summary><strong>product</strong></summary>

| Column | Type | Constraints |
|--------|------|-------------|
| product_id | INT | PK, AUTO_INCREMENT |
| product_name | VARCHAR | NOT NULL |
| description | VARCHAR(500) | |
| image_url | VARCHAR | |
| discount | DOUBLE | CHECK (0 <= val <= 1) |
| unit_price | DOUBLE | CHECK (val >= 0) |
| average_rating | DOUBLE | |
| category_id | INT | FK -> category |

</details>

<details>
<summary><strong>orders</strong></summary>

| Column | Type | Constraints |
|--------|------|-------------|
| order_id | INT | PK, AUTO_INCREMENT |
| customer_id | INT | FK -> customer |
| purchase_date | DATE | |
| unit_amount | DOUBLE | |
| status | VARCHAR | |

</details>

<details>
<summary><strong>order_detail</strong> — composite PK (order_id, product_id)</summary>

| Column | Type | Constraints |
|--------|------|-------------|
| order_id | INT | PK, FK -> orders |
| product_id | INT | PK, FK -> product |
| quantity | INT | |
| is_refunded | BOOLEAN | |

</details>

<details>
<summary><strong>shopping_cart_detail</strong> — composite PK (product_id, customer_id)</summary>

| Column | Type | Constraints |
|--------|------|-------------|
| product_id | INT | PK, FK -> product |
| customer_id | INT | PK, FK -> customer |
| quantity | INT | |

</details>

<details>
<summary><strong>favourites</strong> — composite PK (product_id, customer_id)</summary>

| Column | Type | Constraints |
|--------|------|-------------|
| product_id | INT | PK, FK -> product |
| customer_id | INT | PK, FK -> customer |

</details>

<details>
<summary><strong>review</strong> — composite PK (product_id, customer_id, order_id)</summary>

| Column | Type | Constraints |
|--------|------|-------------|
| product_id | INT | PK, FK -> product |
| customer_id | INT | PK, FK -> customer |
| order_id | INT | PK, FK -> orders |
| rating | INT | |
| description | VARCHAR | |

> UNIQUE constraint on `(product_id, customer_id, order_id)` ensures one review per product per customer per order.

</details>

---

## API Reference

<details>
<summary><strong>Products</strong> — 3 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | List all products (pagination, filtering, sorting) |
| GET | `/products/details/{id}` | Product detail page with reviews |
| GET | `/products/cart/add` | Add product to cart |

</details>

<details>
<summary><strong>Cart</strong> — 9 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/products/cart/add` | Add product to cart |
| GET | `/products/cart/view` | View cart contents |
| POST | `/products/cart/plus` | Increment item quantity |
| POST | `/products/cart/minus` | Decrement item quantity |
| POST | `/products/cart/select` | Toggle item selection |
| POST | `/products/cart/remove` | Remove item from cart |
| POST | `/products/cart/clear` | Clear all items |
| POST | `/products/cart/payment` | Proceed to payment |
| POST | `/products/cart/checkout` | Complete purchase |

</details>

<details>
<summary><strong>Favourites</strong> — 7 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/favourites` | View all favourited products |
| POST | `/favourites/save` | Toggle favourite (add/remove) |
| GET | `/favourites/customer` | Get favourites for logged-in customer |
| POST | `/favourites/clear` | Remove all favourites |
| POST | `/favourites/remove-product` | Remove a single favourite |
| GET | `/favourites/status/{productId}` | Check if product is favourited |
| GET | `/favourites/resume` | Resume pending favourite after login |

</details>

<details>
<summary><strong>Orders</strong> — 2 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/purchaseHistory/customer` | Get order history |
| POST | `/api/purchaseHistory/refund/{order_id}/{product_id}` | Process refund |

</details>

<details>
<summary><strong>Reviews</strong> — 3 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/reviews/add/{productId}/{customerId}/{orderId}` | Add review |
| GET | `/api/reviews/product/{productId}` | Get product reviews |
| GET | `/api/reviews/product/{productId}/average-rating` | Get average rating |

</details>

<details>
<summary><strong>Auth & Account</strong> — 8 endpoints</summary>

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/login` | Login page |
| POST | `/login/try` | Authenticate |
| GET | `/login/logout` | Logout |
| POST | `/login/forgetPassword` | Reset password |
| POST | `/api/register` | Register account |
| GET | `/api/register/check/{userName}` | Check username availability |
| GET | `/api/account-info` | Get account info |
| POST | `/api/account-info/save` | Update account info |

</details>

---

## Getting Started

The instructions below reproduce the original localhost setup. They do not require Railway.

### Prerequisites

| Tool | Version |
|------|---------|
| Java | 17+ |
| Maven | 3.8+ |
| MySQL | 8.0+ |
| Node.js | 18+ |

### Local Installation

```powershell
# 1. Clone the repository
git clone <repository-url>
Set-Location Shopping-Cart-Application

# 2. Create the local MySQL database
mysql -u root -p -e "CREATE DATABASE tst;"

# 3. Set credentials if they differ from the local defaults
$env:SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/tst"
$env:SPRING_DATASOURCE_USERNAME = "root"
$env:SPRING_DATASOURCE_PASSWORD = "root"

# 4. Start the backend (http://localhost:8080)
.\mvnw.cmd spring-boot:run

# 5. In a second PowerShell window, start the React development server
Set-Location shoppingcartfrontend
npm install
npm start
```

The React development server runs at `http://localhost:3000`. The Spring Boot and Thymeleaf application runs at `http://localhost:8080`.

### Test Accounts

| Username | Password |
|----------|----------|
| `jason` | `1234` |
| `glenn` | `abcd` |
| `alice` | `5678` |

---

## My Contribution — Favourites/Wishlist Feature

I owned the **Favourites/Wishlist use case end-to-end**, delivering the feature across all layers of the application stack:

### What I Built

| Layer | Work Done |
|-------|-----------|
| **Entity & Data Model** | Designed the `Favourites` JPA entity with `@IdClass(FavouritesId)` composite primary key modelling the many-to-many relationship between `Customer` and `Product` |
| **Repository** | Wrote custom JPQL queries for fetching favourite products, existence checks, deletion, and count aggregation |
| **Service** | Implemented `@Transactional` business logic with toggle behaviour — a single method that adds if not favourited, removes if already favourited |
| **Controller** | Built 7 REST/MVC endpoints under `/favourites` for view, toggle, bulk clear, single remove, status check, and post-login resume |
| **Frontend** | Created the `favourites.html` Thymeleaf template with responsive product table, quantity selectors, add-to-cart, and remove actions; integrated heart icon toggle on product detail pages with real-time status via `/favourites/status/{id}` |
| **Styling** | Authored `favourites.css` for the wishlist page layout |

### Files Authored (8 files)

```
Model/Favourites.java                    # JPA entity
Model/compositeKey/FavouritesId.java     # Composite primary key
Repository/FavouritesRepository.java     # Data access + JPQL queries
InterfaceMethods/FavouriteService.java   # Service interface (7 methods)
Service/FavouriteServiceImpl.java        # Business logic implementation
Controller/FavouritesController.java     # 7 endpoints
templates/favourites.html                # Wishlist UI
static/css/favourites.css                # Page styling
```

---

## Credits

Built by **Team Two** @ NUS-ISS
