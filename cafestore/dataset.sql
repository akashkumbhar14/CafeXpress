-- Drop and create the database
DROP DATABASE IF EXISTS cafestore_db;
CREATE DATABASE cafestore_db;
USE cafestore_db;

-- Drop existing tables
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS menu_history;

-- Customer table
CREATE TABLE customer(
    cid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(30),
    mobile CHAR(10)
);

-- Menu table
CREATE TABLE menu(
    mid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    description TEXT,
    price DOUBLE,
    category ENUM('Coffee', 'Tea', 'Pastries', 'Snacks', 'Beverages')
);

-- Menu history table (for soft-deleted items)
CREATE TABLE menu_history (
    history_id INT PRIMARY KEY AUTO_INCREMENT,
    mid INT,
    name VARCHAR(30),
    description TEXT,
    price DOUBLE,
    category ENUM('Coffee', 'Tea', 'Pastries', 'Snacks', 'Beverages'),
    deleted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Orders table
CREATE TABLE orders(
    oid INT PRIMARY KEY AUTO_INCREMENT,
    cid INT,
    mid INT,
    FOREIGN KEY (cid) REFERENCES customer(cid),
    FOREIGN KEY (mid) REFERENCES menu(mid)
);

-- Trigger to move deleted menu items to menu_history
DELIMITER $$
CREATE TRIGGER trg_menu_delete
BEFORE DELETE ON menu
FOR EACH ROW
BEGIN
    INSERT INTO menu_history 
        (mid, name, description, price, category) 
    VALUES 
        (OLD.mid, OLD.name, OLD.description, OLD.price, OLD.category);
END $$
DELIMITER ;

-- Sample Cafe Menu items
INSERT INTO menu(name, description, price, category) 
VALUES
    ("Espresso", "Strong and rich espresso shot", 120, 'Coffee'),
    ("Cappuccino", "Espresso with steamed milk and foam", 150, 'Coffee'),
    ("Latte", "Creamy milk coffee with a shot of espresso", 160, 'Coffee'),
    ("Green Tea", "Refreshing hot green tea", 100, 'Tea'),
    ("Masala Chai", "Indian spiced tea with milk", 90, 'Tea'),
    ("Croissant", "Flaky buttery pastry", 70, 'Pastries'),
    ("Chocolate Muffin", "Moist muffin with melted chocolate chips", 90, 'Pastries'),
    ("Veg Sandwich", "Whole wheat sandwich with fresh veggies", 110, 'Snacks'),
    ("Cheese Garlic Bread", "Bread toasted with garlic butter and cheese", 130, 'Snacks'),
    ("Lemonade", "Fresh lemon soda with mint", 80, 'Beverages'),
    ("Iced Tea", "Chilled tea with lemon and mint", 85, 'Beverages'),
    ("Cold Coffee", "Blended cold coffee with ice cream", 140, 'Beverages');

-- Sample query to retrieve orders (with support for deleted menu items)
SELECT 
    o.oid AS Order_ID,
    c.name AS Customer_Name,
    COALESCE(m.name, mh.name) AS Item_Name, 
    COALESCE(m.price, mh.price) AS Price,
    COALESCE(m.category, mh.category) AS Category
FROM 
    orders o
JOIN 
    customer c ON o.cid = c.cid
LEFT JOIN 
    menu m ON o.mid = m.mid
LEFT JOIN 
    menu_history mh ON o.mid = mh.mid AND m.mid IS NULL;
