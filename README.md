# 📚 Student Management System

A simple Java-based command-line application for managing student records using JDBC and Oracle Database.

---

## 🛠️ Technologies Used

- **Java (JDK 1.8)**
- **JDBC (Java Database Connectivity)**
- **Oracle 10g Database**
- **SQL (DDL and DML operations)**
- **Command-Line Interface**
- **Git & GitHub**
- **VS Code**

---

## ⚙️ Features

- ➕ Add a new student (name, roll number, age, email, course)
- 📋 View all students
- 🔍 Search student by ID
- 📝 Update student information
- ❌ Delete student record
- ❎ Exit application safely

---

## 🗃️ Database Schema

Make sure your Oracle DB has the following table:

```sql
CREATE TABLE students (
  id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR2(100),
  roll_no VARCHAR2(50),
  age NUMBER,
  email VARCHAR2(100),
  course VARCHAR2(100)
);
🚀 How to Run
📦 Compile

bash
Copy
Edit
javac -cp ".;lib/ojdbc8.jar" -d bin src/*.java
▶️ Run

bash
Copy
Edit
java -cp ".;lib/ojdbc8.jar;bin" StudentApp
✅ Note: Make sure Oracle 10g is running and connection credentials are correct in StudentApp.java.

📂 Project Structure
python
Copy
Edit
StudentManagement/
│
├── bin/               # Compiled .class files
├── lib/               # ojdbc8.jar (JDBC Driver)
├── src/               # Source code files (.java)
│   └── StudentApp.java
├── db.sql             # Database schema
└── README.md          # This file
🙋‍♂️ Author
Ankit Yadav

📜 License
This project is open-source and free to use for educational purposes.
