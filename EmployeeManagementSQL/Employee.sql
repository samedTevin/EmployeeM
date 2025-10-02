USE employee_management;

SHOW TABLES;

DESC employees;

CREATE TABLE users(
id int AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(10) UNIQUE,
password CHAR(6),
dailyNotes VARCHAR(1000),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

SELECT * FROM users;

SELECT * FROM employees;



SELECT SUM(case WHEN status='Active' THEN 1 ELSE 0 END) as active_count, COUNT(*), AVG(salary), AVG(rating)
FROM employees;
