# Pizza_Shop

## Overview

Pizza_Shop is a Java-based CRUD application developed during the CDAC workshop. The project simulates a pizza shop management system, allowing customers to register, login, view the pizza menu, place orders, and view their order history. Admin users can manage the menu, update prices, view all customers and orders, calculate total profit, and delete pizzas. The application demonstrates core Java concepts, JDBC for database connectivity, and basic SQL for backend data handling.

---

## Features

### Customer Features
- **Registration:** New customers can register by providing their name, email, password, and mobile number.
- **Login:** Existing customers can log in using their email and password.
- **View Pizza Menu:** Customers can browse pizzas by category (Veg Pizza, Non Veg Pizza, Garlic Bread, Drinks).
- **Order Pizza:** Customers can place orders by selecting a pizza from the menu.
- **Order History:** Customers can view their previous orders.

### Admin Features
- **Admin Login:** Admin can log in with predefined credentials.
- **Add Pizza:** Admin can add new pizzas to the menu under different categories.
- **Update Pizza Price:** Admin can update the price of any pizza.
- **View All Customers:** Admin can view a list of all registered customers.
- **View All Orders:** Admin can view all orders placed by customers, including details.
- **Calculate Total Profit:** Admin can view the total profit based on all orders.
- **Delete Pizza:** Admin can delete pizzas from the menu (only if not already ordered).

---

## Folder Structure

```
Pizza_Shop/
│
├── src/
│   └── com/
│       └── dkte/
│           └── pizzashop/
│               ├── dao/         # Data Access Objects for DB operations
│               │   ├── CustomerDao.java
│               │   ├── OrderDao.java
│               │   └── PizzaDao.java
│               ├── entities/    # Entity classes representing DB tables
│               │   ├── Customer.java
│               │   ├── Order.java
│               │   ├── OrderDetalis.java
│               │   └── Pizza.java
│               ├── main/        # Main menu and UI logic
│               │   ├── AdminMenu.java
│               │   ├── Menu.java
│               │   └── SubMenu.java
│               └── utils/       # Utility classes (e.g., DBUtils.java)
│
├── db.sql           # SQL script to create and populate the database
├── dataset.sql      # Extended SQL with triggers and history table
├── README.md        # Project documentation
└── .classpath, .project, .settings/  # Eclipse project files
```

---

## Code Structure and Explanation

### 1. **DAO Layer**
- [`CustomerDao`](src/com/dkte/pizzashop/dao/CustomerDao.java): Handles CRUD operations for customers.
- [`PizzaDao`](src/com/dkte/pizzashop/dao/PizzaDao.java): Handles CRUD operations for pizzas/menu items.
- [`OrderDao`](src/com/dkte/pizzashop/dao/OrderDao.java): Handles order placement, order history, and profit calculation.

### 2. **Entities**

- [`Customer`](src/com/dkte/pizzashop/entities/Customer.java):  
  This class is responsible for storing and managing all information related to a customer. It includes fields for customer ID, name, email, password, and mobile number. The class provides methods for accepting user input (used during registration), comparing customers (for login authentication), and displaying customer details. It also overrides `equals` and `hashCode` to ensure proper comparison based on email and password, which is useful for login validation.

- [`Pizza`](src/com/dkte/pizzashop/entities/Pizza.java):  
  This class represents each pizza or menu item available in the shop. It contains fields for the menu ID, pizza name, description, and price. The class includes methods for accepting pizza details from the admin (when adding or updating pizzas) and for displaying pizza information to users. This structure allows the menu to be dynamic and easily managed by the admin, and ensures customers always see up-to-date menu options.

- [`Order`](src/com/dkte/pizzashop/entities/Order.java):  
  This class models a customer's order. It holds the order ID, the customer ID (who placed the order), and the menu ID (which pizza was ordered). It provides a simple structure for creating and tracking orders, making it easy to link customers to their purchases and manage order history. The class also includes a `toString` method for displaying order details in a readable format.

- [`OrderDetalis`](src/com/dkte/pizzashop/entities/OrderDetalis.java):  
  This class is used to present detailed information about each order, especially for admin views and order history. It includes the order ID, customer ID, customer name, pizza name, and price. By combining data from multiple tables, it helps display comprehensive order summaries, making it easier for admins to review all transactions and for customers to see their past orders. This class is particularly useful for generating reports and detailed listings in the admin panel.

### 3. **Main/UI Layer**
- [`Menu`](src/com/dkte/pizzashop/main/Menu.java): Entry point for the application, handles main menu, login, registration, and admin login.
- [`SubMenu`](src/com/dkte/pizzashop/main/SubMenu.java): Handles customer-specific actions after login (view menu, order, history).
- [`AdminMenu`](src/com/dkte/pizzashop/main/AdminMenu.java): Handles admin-specific actions (add/update/delete pizza, view customers/orders, profit).

### 4. **Utilities**
- [`DBUtils`](src/com/dkte/pizzashop/utils/DBUtils.java): Provides a method to establish a connection to the MySQL database.

### 5. **Database Scripts**
- [`db.sql`](db.sql): Creates the database schema and populates initial data.
- [`dataset.sql`](dataset.sql): Adds advanced features like a menu history table and triggers for tracking deleted menu items.

---

## What You Will Learn

- **Java OOP Concepts:** Classes, objects, encapsulation, and inheritance.
- **JDBC:** Connecting Java applications to MySQL, executing SQL queries, and handling results.
- **CRUD Operations:** Implementing Create, Read, Update, Delete for multiple entities.
- **Menu-driven Console Application:** Designing user-friendly command-line interfaces.
- **Exception Handling:** Managing SQL and input exceptions gracefully.
- **Database Design:** Creating normalized tables, using foreign keys, and writing triggers.
- **Project Structure:** Organizing code into packages for maintainability and clarity.

---

## How to Run

1. **Set up MySQL Database:**
   - Run the `db.sql` or `dataset.sql` script in your MySQL server to create and populate the database.

2. **Configure Database Connection:**
   - Ensure the credentials in [`DBUtils.java`](src/com/dkte/pizzashop/utils/DBUtils.java) match your MySQL setup.

3. **Build and Run:**
   - Open the project in Eclipse or any Java IDE.
   - Add the MySQL JDBC driver to your project libraries.
   - Run the `Menu` class to start the application.

---

## Admin Credentials

- **Email:** admin@gmail.com
- **Password:** admin

---

## Conclusion

This project demonstrates a complete workflow for a CRUD-based Java application with a real-world use case. It covers both backend (database) and frontend (console UI) aspects, making it a great learning resource for interviews and practical Java development.

---