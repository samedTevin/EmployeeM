CREATE DATABASE employee_management;


USE employee_management;

SHOW TABLES;

CREATE TABLE users(
id int AUTO_INCREMENT PRIMARY KEY,
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
    rating DOUBLE
);

SELECT * FROM users;

SELECT * FROM employees;



SELECT SUM(case WHEN status='Active' THEN 1 ELSE 0 END) as active_count, COUNT(*), AVG(salary), AVG(rating)
FROM employees;
