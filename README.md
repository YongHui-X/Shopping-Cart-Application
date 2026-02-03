# Run the Application

1. **Clone repo**
2. **Open MySQL**  
   Start your MySQL server.

3. **Initialise the Database**  
   Create the required database and tables (if not already done).

4. **Run the Application**  
   Start the Spring Boot app and access it at:  

# Lifecycle:
## V1.001
### 03/02 15:03 
### Modified by : Yong Hui
### Definitions
* @Data is the function of lombok. it will create getter and setter automatically, so you have no need to type in by yourself.
* @Setter(AccessLevel.NONE) means tell the system not create the setter for this attribute. For example id is primary key and you can not modify by yourself. So no need to create setter().
* @IdClass is used for composite key. Here We have composite key. If just using @Id may have problem. The @IdClass annotation in Java Persistence API (JPA) is used to define a composite primary key for an entity. A composite primary key consists of multiple fields that together uniquely identify an entity. The @IdClass annotation specifies a separate class to represent this composite key.


## Design Implementation Guide
### Modified by : Glenn
### Do not temper with this.  

## 🧭 Framework - Bootstrap 5.3.8

## 🎨 Design System Components

### 🎨 Color Palette

| Role                 | Hex       | Description                    |
| -------------------- | --------- | ------------------------------ |
| **Primary**          | `#0d6efd` | Bootstrap Blue                 |
| **Secondary**        | `#6c757d` | Gray                           |
| **Dark Background**  | `#212529` | Dark gray / black tone         |
| **Light Background** | `#f8f9fa` | Soft white                     |
| **Success**          | `#198754` | Green (for success messages)   |
| **Danger**           | `#dc3545` | Red (for errors / alerts)      |
| **Warning**          | `#ffc107` | Yellow (for caution / prompts) |

### ✍️ Typography

* **Font Family**: System fonts (`-apple-system`, `BlinkMacSystemFont`, `"Segoe UI"`, `Roboto`, etc.)
* **Headings**: Bold (`600–700`)
* **Body Text**: Regular (`400`)
* **Small Text**: `0.875rem`

---

## 🗂️ File Structure

```
project/
├── static/
│   ├── css/
│   │   ├── style.css                 (Global styles – BASE)
│   │   ├── cart.css                  (Cart styles)
│   │   ├── displayProducts.css       (Product listing styles)
│   │   ├── detailsProducts.css       (Product details styles)
│   │   ├── login.css                 (Login / signup styles)
│   │   └── checkout.css              (Checkout styles)
│   ├── js/
│   └── images/
└── templates/
    ├── cart.html
    ├── displayProducts.html
    ├── detailsProducts.html
    ├── login.html
    ├── createAccount.html
    ├── login_error.html
    └── checkout.html
```

---

## ⚙️ Implementation Steps

### 🧩 Step 1 – Replace CSS Files

1. Replace your **`style.css`** with the new global styles.
2. Update all **page-specific CSS** (`cart.css`, `displayProducts.css`, etc.).
3. Ensure all pages **link to `style.css` first**, then their page-specific CSS.

---

### 🧱 Step 2 – Update HTML Structure

All HTML files should follow this template:

```html
<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Shop @ISS – Page Title</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css" rel="stylesheet">

  <!-- Custom Styles -->
  <link href="../static/css/style.css" rel="stylesheet">
  <link href="../static/css/page-specific.css" rel="stylesheet">
</head>
<body>
  <!-- Page content goes here -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
```

---

### 🧭 Step 3 – Standard Page Components

#### 🔝 Header

```html
<header class="site-header">
  <div class="container">
    <div class="row align-items-center">
      <div class="col-md-3">
        <h5 class="logo">Shop @ISS</h5>
      </div>
      <div class="col-md-6 text-center d-none d-md-block">
        <span class="promotional-text">
          Today's Deals: Free shipping over $80 | 10% off new arrivals
        </span>
      </div>
      <div class="col-md-3">
        <div class="user-actions d-flex align-items-center justify-content-end gap-2">
          <a href="#" class="text-white"><i class="bi bi-person-circle fs-4"></i></a>
          <a th:href="@{/login}" class="btn btn-outline-light btn-sm">Login</a>
          <a th:href="@{/signup}" class="btn btn-warning btn-sm">Sign Up</a>
        </div>
      </div>
    </div>
  </div>
</header>
```

#### 🧭 Navigation

```html
<nav class="navbar navbar-expand-lg navbar-dark site-navbar">
  <div class="container">
    <a class="navbar-brand" th:href="@{/products}">
      <i class="bi bi-grid-3x3-gap me-2"></i>Browse
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item"><a class="nav-link" th:href="@{/settings}"><i class="bi bi-gear me-1"></i>Settings</a></li>
        <li class="nav-item"><a class="nav-link" th:href="@{/favorites}"><i class="bi bi-heart-fill me-1"></i>Favorites</a></li>
        <li class="nav-item"><a class="nav-link" th:href="@{/cart}"><i class="bi bi-cart3 me-1"></i>Cart</a></li>
      </ul>
    </div>
  </div>
</nav>
```

#### ⚓ Footer

```html
<footer class="site-footer">
  <div class="container text-center">
    <p>&copy; 2025 TEAM_TWO. All rights reserved.</p>
  </div>
</footer>
```

---

## 💡 Key Design Features

1. **Consistent Header & Navigation** – identical structure on all pages
2. **Card-Based Layouts** – Bootstrap cards with shadows and hover effects
3. **Form Styling** – floating labels, clear validation, consistent input sizes
4. **Button Hierarchy**
   * Primary → `.btn-primary`
   * Secondary → `.btn-outline-primary`
   * Destructive → `.btn-outline-danger`
5. **Spacing System** – use Bootstrap utilities (`mt-3`, `p-5`, etc.)

---

## 📱 Responsive Design

| Breakpoint        | Range         | Usage                      |
| ----------------- | ------------- | -------------------------- |
| **Mobile**        | `< 576px`     | Stack layouts, hide promos |
| **Tablet**        | `576 – 768px` | Two-column layouts         |
| **Desktop**       | `> 768px`     | Full layouts               |
| **Large Desktop** | `> 992px`     | Wide containers            |

### 📲 Mobile Optimizations

* Hide promotional text
* Stack forms vertically
* Collapsible navbar
* Scrollable tables

---

## 🌐 Browser Compatibility

✅ Chrome  |  ✅ Firefox  |  ✅ Safari  |  ✅ Edge  |  ✅ Mobile browsers (iOS & Android)

---

## ✅ Testing Checklist

* [ ] CSS loads without errors
* [ ] Header / Navbar consistent
* [ ] Forms validate properly
* [ ] Buttons hover correctly
* [ ] Cards show proper borders & shadows
* [ ] Mobile navbar works
* [ ] Footer pinned at bottom
* [ ] Colors follow palette
* [ ] Typography consistent
* [ ] Icons visible

---

## 🧰 Common Issues & Fixes

| Issue                 | Cause               | Solution                                |
| --------------------- | ------------------- | --------------------------------------- |
| CSS not loading       | Wrong path          | Use `../static/css/` for relative links |
| Icons missing         | Missing CDN         | Include Bootstrap Icons CDN in `<head>` |
| Navbar not collapsing | Missing JS bundle   | Ensure Bootstrap JS bundle is loaded    |
| Inconsistent spacing  | Custom CSS override | Use Bootstrap spacing utilities         |

---

## 🌟 Best Practices

1. **Always load `style.css` first**
2. **Prefer Bootstrap classes before custom CSS**
3. **Keep custom CSS minimal & brand-focused**
4. **Test on multiple devices**
5. **Use semantic HTML** (`header`, `nav`, `main`, `footer`)
6. **Ensure accessibility** (ARIA labels, alt text)
7. **Use clear validation for forms**

---

## 🧩 Support & References

* 📘 [Bootstrap 5.3 Docs](https://getbootstrap.com/docs/5.3/)
* 💠 [Bootstrap Icons](https://icons.getbootstrap.com/)
