CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE IF NOT EXISTS vehicle (
--    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) PRIMARY KEY,
    wheels VARCHAR(10)
);
