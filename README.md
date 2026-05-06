# Product Registration System (Java + MySQL)

## 📌 Overview

This is a desktop-based Product Registration System developed using Java Swing and MySQL.
It allows users to manage product data with basic CRUD operations (Create, Read, Update, Delete).

---

## 🚀 Features

* Add new products
* View all products
* Update product price
* Delete product
* Search products by name

---

## 🛠️ Technologies Used

* Java (Swing for GUI)
* JDBC (Java Database Connectivity)
* MySQL Database

---

## 📂 Project Structure

```
Product-Registration-System/
│── ProductGUI.java
│── mysql-connector-j-9.7.0.jar
│── README.md
```

---

## ⚙️ Setup Instructions

### 1. Install Requirements

* Java JDK (8 or above)
* MySQL Server
* VS Code or any IDE

---

### 2. Create Database

Run the following SQL in MySQL:

```sql
CREATE DATABASE product_db;

USE product_db;

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    category VARCHAR(50),
    price DOUBLE,
    quantity INT,
    reg_date DATE
);
```

---

### 3. Configure Database in Code

Open `ProductGUI.java` and check:

```java
static final String URL = "jdbc:mysql://localhost:3306/product_db";
static final String USER = "root";
static final String PASS = "";
```

Update username/password if needed.

---

### 4. Add MySQL Connector

Make sure `mysql-connector-j-9.7.0.jar` is added to your project classpath.

---

### 5. Run the Project

Compile and run:

```bash
javac ProductGUI.java
java ProductGUI
```

---

## 🖥️ Output

* GUI-based form for entering product details
* Buttons for Insert, Display, Update, Delete, and Search
* Output shown in text area

---

## ⚠️ Notes

* Enter date in format: `YYYY-MM-DD`
* Ensure MySQL server is running before execution
* Do not upload `.class` files to GitHub

---

## 📌 Future Improvements

* Add login system
* Improve UI design
* Add category filters and reports
* Export data to Excel/PDF

---

## 👩‍💻 Author
Harshada Mhetre
B.tech CSE Student

---

## 📄 License

This project is for educational purposes.
