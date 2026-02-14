# Shop @ISS — E-Commerce Shopping Cart

Full-stack e-commerce web application built with Spring Boot and React.

---

## Screenshots

### Login
<img width="731" height="433" alt="login" src="https://github.com/user-attachments/assets/91e12dbd-8692-4d5b-b9c0-37e8d1c8cbd6" />

### Product Homepage
<img width="782" height="473" alt="products" src="https://github.com/user-attachments/assets/07b36a52-08f4-4795-8c66-2618be6cbcaf" />

### Favourites
<img width="733" height="364" alt="favs" src="https://github.com/user-attachments/assets/12fc91c3-0624-4d41-be9e-44c6367cf84b" />

### Product Details
<img width="707" height="424" alt="detailed products" src="https://github.com/user-attachments/assets/580a99ed-7dec-4d31-a150-2fd7338a34e6" />

### Add to Cart
<img width="704" height="266" alt="shopping cart" src="https://github.com/user-attachments/assets/abefaff8-dd7f-4c29-a372-98b52e323884" />

### Payment Completion
<img width="700" height="425" alt="payment " src="https://github.com/user-attachments/assets/86de5ef8-e199-4ef7-be5c-ad5af7c3c964" />

---

## Features

- Product browsing with search, category filtering, sorting (name/price/rating), and pagination
- Shopping cart with quantity management, discount calculation, and selective checkout
- User authentication with session management
- Order history and refund processing
- Product reviews and star ratings
- Favourites / wishlist
- Account management (profile editing, password recovery)
- Responsive design (mobile, tablet, desktop)

---

## Tech Stack

| Layer | Technologies |
|---|---|
| **Backend** | Java 17, Spring Boot 3.5.6, Spring Data JPA, Thymeleaf, Maven |
| **Frontend** | React 19, React Router, React Bootstrap, Axios |
| **Database** | MySQL 8, Spring Session JDBC |
| **Styling** | Bootstrap 5.3, Bootstrap Icons |

---

## Architecture Overview

The application follows an MVC + service-layer architecture: **Controller → Service → Repository**.

- **9 Controllers** — ProductController, ShoppingCartDetailController, OrdersController, FavouritesController, ReviewController, LogController, RegisterController, AccountInfoController, CategoryController
- **9 Services** — Business logic implementations for each domain
- **9 Repositories** — Spring Data JPA interfaces (including CustomerRepository and OrderDetailRepository)
- **8 Entities** — Product, Customer, Category, ShoppingCartDetail, Orders, OrderDetail, Review, Favourites (with composite key support via `@IdClass`)

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL 8
- Node.js + npm

### 1. Clone the repository

```bash
git clone <repository-url>
cd Shopping-Cart-Application
```

### 2. Create the MySQL database

```sql
CREATE DATABASE tst;
```

### 3. Configure database credentials

Update `src/main/resources/application.properties` with your MySQL username and password:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tst
spring.datasource.username=root
spring.datasource.password=root
```

### 4. Run the backend

```bash
mvn spring-boot:run
```

The backend will start at **http://localhost:8080**.

### 5. Run the frontend

```bash
cd shoppingcartfrontend
npm install
npm start
```

The React frontend will start at **http://localhost:3000**.

### 6. Test accounts

| Username | Password |
|---|---|
| `jason` | `1234` |
| `glenn` | `abcd` |
| `alice` | `5678` |

---

## API Endpoints

### Products

| Method | Endpoint | Description |
|---|---|---|
| GET | `/products` | List all products (pagination, filtering, sorting) |
| GET | `/products/details/{id}` | Product detail with reviews |
| GET | `/products/cart/add` | Add product to cart |

### Cart

| Method | Endpoint | Description |
|---|---|---|
| POST | `/products/cart/add` | Add product to cart |
| GET | `/products/cart/view` | View cart contents |
| POST | `/products/cart/plus` | Increment item quantity |
| POST | `/products/cart/minus` | Decrement item quantity |
| POST | `/products/cart/select` | Toggle item selection |
| POST | `/products/cart/remove` | Remove item from cart |
| POST | `/products/cart/clear` | Clear all items |
| POST | `/products/cart/payment` | Proceed to payment |
| POST | `/products/cart/checkout` | Complete purchase |

### Orders

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/purchaseHistory/customer` | Get order history |
| POST | `/api/purchaseHistory/refund/{order_id}/{product_id}` | Process refund |

### Reviews

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/reviews/add/{productId}/{customerId}/{orderId}` | Add review |
| GET | `/api/reviews/product/{productId}` | Get product reviews |
| GET | `/api/reviews/product/{productId}/average-rating` | Get average rating |

### Favourites

| Method | Endpoint | Description |
|---|---|---|
| GET | `/favourites` | List favourites |
| POST | `/favourites/save` | Toggle favourite |
| POST | `/favourites/remove-product` | Remove favourite |
| GET | `/favourites/status/{productId}` | Check favourite status |
| POST | `/favourites/clear` | Clear all favourites |

### Auth & Account

| Method | Endpoint | Description |
|---|---|---|
| GET | `/login` | Login page |
| POST | `/login/try` | Authenticate |
| GET | `/login/logout` | Logout |
| POST | `/login/forgetPassword` | Reset password |
| POST | `/api/register` | Register account |
| GET | `/api/register/check/{userName}` | Check username availability |
| GET | `/api/account-info` | Get account info |
| POST | `/api/account-info/save` | Update account info |

---

## Project Structure

```
Shopping-Cart-Application/
├── pom.xml
├── src/main/java/com/Assignment/shopping_carts/
│   ├── Controller/          # 9 controllers (MVC + REST)
│   ├── Service/             # 9 service implementations
│   ├── Repository/          # 9 JPA repository interfaces
│   ├── Model/               # 8 entity classes
│   ├── DTO/                 # CustomerRegisterDTO
│   └── Config/              # CorsConfig, WebAppConfig
├── src/main/resources/
│   ├── application.properties
│   ├── templates/           # Thymeleaf views
│   │   ├── displayProducts.html
│   │   ├── detailsProducts.html
│   │   ├── shoppingCart.html
│   │   ├── checkout.html
│   │   ├── creditCardDetails.html
│   │   ├── favourites.html
│   │   ├── settings.html
│   │   ├── login.html
│   │   ├── login_error.html
│   │   ├── createAccount.html
│   │   └── forgetPassword.html
│   └── static/
│       ├── css/             # Page-specific stylesheets
│       └── images/          # Screenshots and assets
└── shoppingcartfrontend/    # React SPA
    └── src/
        ├── components/      # Header, NavBar, Sidebar, Favourites
        ├── pages/           # AccountInfo, PurchaseHistory, Register
        └── css/             # Frontend stylesheets
```

---

<details>
<summary>Design System</summary>

### Color Palette

| Role | Hex | Description |
|---|---|---|
| Primary | `#0d6efd` | Bootstrap Blue |
| Secondary | `#6c757d` | Gray |
| Dark Background | `#212529` | Dark gray / black tone |
| Light Background | `#f8f9fa` | Soft white |
| Success | `#198754` | Green |
| Danger | `#dc3545` | Red |
| Warning | `#ffc107` | Yellow |

### Static Assets

```
static/
├── css/
│   ├── style.css              # Global styles
│   ├── cart.css
│   ├── checkout.css
│   ├── detailsProducts.css
│   ├── displayProducts.css
│   ├── favourites.css
│   ├── login.css
│   └── settings.css
└── images/
    ├── shop-logo.png
    └── placeholder.png
```

</details>

---

## My Contribution — Favourites/Wishlist Feature

I was responsible for designing and implementing the **Favourites (Wishlist)** use case end-to-end across all layers of the application:

### Backend (Spring Boot)
- **Entity & Composite Key** — Created the `Favourites` JPA entity with `@IdClass(FavouritesId)` to model the many-to-many relationship between `Customer` and `Product` using a composite primary key (`customerId` + `productId`)
- **Repository** — Wrote custom JPQL queries for fetching favourite products by customer, existence checks, deletion operations, and count aggregation (`FavouritesRepository`)
- **Service Layer** — Implemented transactional business logic with toggle behaviour: a single `saveFavourites()` method that adds a product if not yet favourited, or removes it if already favourited (`FavouriteServiceImpl`)
- **Controller** — Built 7 endpoints under `/favourites` handling view, toggle, bulk clear, single remove, status check, and a post-login resume flow for unauthenticated users (`FavouritesController`)

### Frontend (Thymeleaf)
- **Favourites Page** (`favourites.html`) — Responsive table view displaying all favourited products with images, pricing, quantity selectors, add-to-cart, view details, and remove buttons
- **Heart Icon Toggle** — Integrated a favourite toggle button on the product details page with real-time status checking via the `/favourites/status/{id}` endpoint

### Endpoints Implemented

| Method | Endpoint                      | Description                            |
|--------|-------------------------------|----------------------------------------|
| GET    | `/favourites`                 | View all favourited products           |
| POST   | `/favourites/save`            | Toggle favourite (add/remove)          |
| GET    | `/favourites/customer`        | Get favourites for logged-in customer  |
| POST   | `/favourites/clear`           | Remove all favourites for a customer   |
| POST   | `/favourites/remove-product`  | Remove a single favourited product     |
| GET    | `/favourites/status/{id}`     | Check if a product is favourited       |
| GET    | `/favourites/resume`          | Resume pending favourite after login   |

### Files Authored
- `Model/Favourites.java` + `Model/compositeKey/FavouritesId.java`
- `Repository/FavouritesRepository.java`
- `InterfaceMethods/FavouriteService.java`
- `Service/FavouriteServiceImpl.java`
- `Controller/FavouritesController.java`
- `templates/favourites.html` + `static/css/favourites.css`

---

## Credits

Built by **Team Two** @ NUS-ISS
