# Employee Management System 💼

A desktop application for managing employees with JavaFX and MySQL. My first complete software project.

![Java](https://img.shields.io/badge/Java-24-orange?style=flat&logo=java)
![JavaFX](https://img.shields.io/badge/JavaFX-25-blue?style=flat)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat&logo=mysql)

## ✨ Features

- 👤 User authentication (register/login)
- ➕ Full CRUD operations for employees
- 🔍 Search functionality
- 📊 Interactive reports with charts (Bar, Pie, Scatter)
- 📈 Statistics dashboard
- 🎨 Modern UI with custom CSS
- 📝 User profiles with daily notes

## 🛠️ Technologies

- Java 24
- JavaFX 25
- MySQL 8.0
- JDBC
- MVC Architecture
- Repository Pattern

## 💻 Quick Setup

1. **Clone repository**
```bash
git clone https://github.com/yourusername/employee-management-system.git
```

2. **Set up database**
```sql
CREATE DATABASE employee_management;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(10) UNIQUE,
    password CHAR(6),
    dailyNotes VARCHAR(1000),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE employees(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    position VARCHAR(100),
    department VARCHAR(50),
    salary DOUBLE,
    status VARCHAR(20),
    rating DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

3. **Configure database credentials** in `src/utils/Database.java`

4. **Run** `src/main/Main.java`

## 📁 Project Structure

```
EmployeeM/
├── src/
│   ├── controller/            # UI Controllers
│   │   ├── DashboardController.java
│   │   ├── EmployeeController.java
│   │   ├── LoginController.java
│   │   ├── ProfileController.java
│   │   ├── RegisterController.java
│   │   ├── ReportController.java
│   │   └── UpdateEmployeeController.java
│   ├── model/                 # Data Models
│   │   ├── Employee.java
│   │   ├── User.java
│   │   └── DepartmentStats.java
│   ├── repository/            # Database Operations
│   │   ├── EmployeeRepository.java
│   │   ├── UserRepository.java
│   │   └── ReportRepository.java
│   ├── helper/                # Utilities
│   │   ├── Alerts.java
│   │   ├── SceneChanger.java
│   │   ├── TextFieldUtils.java
│   │   └── UserSession.java
│   ├── utils/
│   │   └── Database.java
│   ├── view/                  # FXML & CSS
│   │   ├── css/
│   │   │   ├── dashboard.css
│   │   │   ├── dialog.css
│   │   │   ├── employee.css
│   │   │   ├── login.css
│   │   │   ├── profile.css
│   │   │   ├── register.css
│   │   │   └── report.css
│   │   ├── Dashboard.fxml
│   │   ├── Employee.fxml
│   │   ├── Login.fxml
│   │   ├── Profile.fxml
│   │   ├── Register.fxml
│   │   ├── Report.fxml
│   │   └── UpdateEmployeeForm.fxml
│   └── main/
│       └── Main.java
└── EmployeeManagementSQL/
    └── Employee.sql
```

## ⚠️ Known Limitations

**Security Issues (Learning Project Only):**
- Passwords stored in plain text
- Hardcoded database credentials
- No password hashing

**Code Quality:**
- No unit tests
- No logging framework
- Manual resource management

**⚠️ NOT for production use**

## 📝 Note

This is my **first complete software project**, built for learning purposes. Feedback and suggestions are welcome!

---

⭐ If you found this helpful, please star the repo!
