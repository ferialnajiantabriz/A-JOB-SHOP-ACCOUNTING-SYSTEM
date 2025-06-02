
# 🏭 Job-Shop Accounting System

This repository contains an **individual project** developed for **CS-4513: Database Management Systems** (Fall 2023) at the University of Oklahoma. The project models and manages operations of a **job-shop accounting system** including customer orders, job tracking, process management, and cost accounting.

---

## 📌 Project Overview

The system simulates a manufacturing environment with components such as:

- Customers and orders
- Processes (fit, paint, cut)
- Jobs and their assignments
- Department and cost supervision
- Accounting records and transactions

Designed with scalability and normalization in mind, the system handles CRUD operations, cost analysis, and reporting.

---

## 🛠 Technologies Used

- **Database**: Azure SQL Database  
- **Backend**: Java (JDBC)  
- **Frontend**: HTML/CSS (basic UI for interaction)  
- **Language**: SQL (DDL, DML, T-SQL)

---

## 🧱 Database Features

- **Entity-Relationship (ER) Diagram**  
- **Normalized Relational Schema**  
- **Table Creation Scripts** with Foreign Keys and Constraints  
- **Indexing Strategies** (heap, hash, index sequential)  
- **Azure SQL Storage Optimization**

---

## 📊 SQL Functionalities

- Insert, update, delete, and complex select queries
- Aggregate queries for cost/labor computation
- Range and random access support
- Stored procedures and transaction simulations
- Error checking and validation (FK violations, data types, etc.)

---

## 💻 Java Integration

- Java source code for executing SQL queries
- JDBC setup for connecting to Azure SQL
- Testing scripts for all queries
- Screenshot documentation of successful query execution

---

## 🌐 Web Interface

- Minimal HTML/CSS frontend
- Supports customer listing, insertion, filtering (by category)
- Import/export operations
- Exit interface and error testing

---

## 📂 Project Structure

```

📁 JobShop-Accounting-System/
├── sql/
│   └── create\_tables.sql
│   └── insert\_queries.sql
│   └── advanced\_queries.sql
├── java/
│   └── JobShopApp.java
│   └── db\_config.properties
├── web/
│   └── index.html
│   └── style.css
├── screenshots/
│   └── \*.png
├── report/
│   └── IP\_Report\_Ferial\_Najiantabriz.pdf
└── README.md

```

---

## 🧪 Testing & Results

- Queries tested in Azure SQL
- Java program tested with different inputs and edge cases
- Screenshots included for:
  - Successful queries
  - Web application usage
  - Error scenarios

---

## 👩‍💻 Author

**Ferial Najiantabriz**  
Email: [najiantabriz.ferial@gmail.com](mailto:najiantabriz.ferial@gmail.com)  
University of Oklahoma

---

## 🧾 License

This project is for academic use only and follows the University of Oklahoma’s code of conduct and academic integrity policy.

---

## 📎 Acknowledgments

Special thanks to **Dr. Le Gruenwald** for guidance and support throughout the course and project.

