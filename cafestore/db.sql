DROP DATABASE IF EXISTS pizzastore_db;
CREATE DATABASE pizzastore_db;
USE pizzastore_db;

DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS orders;

CREATE TABLE customer(
    cid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15),
    email VARCHAR(50),
    password VARCHAR(30),
    mobile CHAR(10)
);

CREATE TABLE menu(
    mid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    description TEXT,
    category VARCHAR(30),
    price DOUBLE
);

CREATE TABLE orders(
    oid INT PRIMARY KEY AUTO_INCREMENT,
    cid INT,
    mid INT,
    FOREIGN KEY (cid) REFERENCES customer(cid),
    FOREIGN KEY (mid) REFERENCES menu(mid)
);

INSERT INTO menu(name,description,price,category) VALUES("Margherita", "Classic delight with 100% real mozzarella cheese", 109, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Farmhouse", "Delightful combination of onion, capsicum, tomato & grilled mushroom", 459, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Peppy Paneer", "Flavorful trio of juicy paneer, crisp capsicum with spicy red paprika", 259, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Mexican Green Wave", "Mexican herbs sprinkled on onion, capsicum, tomato & jalapeno", 259, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Veggie Paradise", "The awesome foursome! Golden corn, black olives, capsicum, red paprika", 689, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Paneer Makhani", "Paneer, Onion & Capsicum with Desi Makhani Sauce", 129, 'Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Chicken Tikka", "Succulent chicken tikka with onions and capsicum", 349, 'Non Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Pepperoni", "Classic pepperoni with spicy tomato sauce", 399, 'Non Veg Pizza');
INSERT INTO menu(name,description,price,category) VALUES("Garlic Bread", "Buttery garlic bread with herbs", 89, 'Garlic Bread');
INSERT INTO menu(name,description,price,category) VALUES("Garlic Bread with Cheese", "Buttery garlic bread with cheese and herbs", 129, 'Garlic Bread');
INSERT INTO menu(name,description,price,category) VALUES("Coca-Cola", "Refreshing Coca-Cola", 50, 'Drinks');
INSERT INTO menu(name,description,price,category) VALUES("Pepsi", "Refreshing Pepsi", 50, 'Drinks');

