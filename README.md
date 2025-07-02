# CafeXpress ☕

## Overview

**CafeXpress** is a Java-based CRUD console application developed as part of a CDAC workshop. It simulates a cafe management system where:

- Customers can **register**, **log in**, **browse menu items** (like Coffee, Snacks, Beverages, etc.), **place orders**, and **view their order history**.
- Admins can **manage menu items**, **update prices**, **view all customers and orders**, **calculate total profit**, and **delete menu items** (only if not ordered).

This project demonstrates **core Java concepts**, **JDBC**, and **SQL**, providing a strong foundation for building database-driven applications.

---

## ✨ Features

### ☕ Customer Features
- **Registration:** Sign up using name, email, password, and mobile number.
- **Login:** Authenticate using email and password.
- **View Menu:** Browse cafe items by category (Coffee, Snacks, Bakery, Beverages).
- **Place Order:** Select menu items by ID to place an order.
- **Order History:** View all previous orders.

### 🛠️ Admin Features
- **Admin Login:** Use predefined admin credentials.
- **Add Menu Item:** Insert new items under chosen categories.
- **Update Item Price:** Modify the price of an item.
- **View All Customers:** Display a list of registered customers.
- **View All Orders:** See all orders placed with details.
- **Calculate Total Profit:** Get total revenue generated.
- **Delete Menu Item:** Remove items not yet ordered.

---

## 📁 Folder Structure

CafeXpress/
│
├── src/
│ └── com/
│ └── cafexpress/
│ ├── dao/ # DAO classes for DB operations
│ │ ├── CustomerDao.java
│ │ ├── OrderDao.java
│ │ └── MenuItemDao.java
│ ├── entities/ # Entity/POJO classes
│ │ ├── Customer.java
│ │ ├── Order.java
│ │ ├── OrderDetails.java
│ │ └── MenuItem.java
│ ├── main/ # UI & menu logic
│ │ ├── AdminMenu.java
│ │ ├── Menu.java
│ │ └── SubMenu.java
│ └── utils/
│ └── DBUtils.java
│
├── db.sql # SQL schema + sample data
├── dataset.sql # Extended SQL with triggers/history
├── README.md # Project documentation
└── .classpath, .project, .settings/ # Eclipse project configs


---

## 🧩 Code Modules

### 1. DAO Layer
- **CustomerDao:** Handles registration, login, and customer listing.
- **MenuItemDao:** Manages CRUD operations for cafe menu items.
- **OrderDao:** Handles order placement, history, and profit calculations.

### 2. Entity Classes
- **Customer.java**  
  Stores customer info like ID, name, email, password, and mobile.

- **MenuItem.java**  
  Represents menu items with ID, name, description, category, and price.

- **Order.java**  
  Maps customer orders with customer ID and menu item ID.

- **OrderDetails.java**  
  Used to display comprehensive order info (order ID, customer name, item name, price).

### 3. UI / Main Logic
- **Menu.java**  
  Entry point. Offers login/register/admin options.

- **SubMenu.java**  
  Customer dashboard post-login (view menu, order items, view history).

- **AdminMenu.java**  
  Admin dashboard to manage menu, view customers/orders, and profit stats.

### 4. Utilities
- **DBUtils.java**  
  Handles MySQL connection logic.

---

## 🗃️ Database Setup

- **`db.sql`**:  
  Creates all necessary tables (`customer`, `menu`, `orders`) with sample data.

- **`dataset.sql`**:  
  Adds extra features such as triggers and a deleted item history table.

---

## 📚 What You Will Learn

- Object-Oriented Programming (OOP) in Java.
- JDBC for connecting Java apps with MySQL.
- Building a modular CRUD application.
- Exception handling and input validation.
- Writing and executing SQL joins, foreign keys, triggers.
- Multi-role access handling (Customer vs Admin).
- Code structuring in layered architecture.

---

## ▶️ How to Run

1. **Set Up MySQL:**
   - Import `db.sql` or `dataset.sql` into your MySQL database.

2. **Update DB Connection:**
   - In `DBUtils.java`, replace:
     ```java
     String URL = "jdbc:mysql://localhost:3306/cafedb";
     String USERNAME = "root";
     String PASSWORD = "your_mysql_password";
     ```

3. **Open in IDE (Eclipse/IntelliJ):**
   - Ensure `mysql-connector-java` is added to the build path.

4. **Run the App:**
   - Execute the `Menu.java` file.

---

## 🔐 Admin Credentials

Email : admin@gmail.com
Password : admin

## Conclusion
CafeXpress is a practical Java console application that showcases core concepts like JDBC, CRUD operations, and role-based access (Customer/Admin). It's a great mini-project for learning Java, preparing for interviews, and understanding real-world application structure.