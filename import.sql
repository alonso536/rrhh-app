DROP DATABASE IF EXISTS rrhhdb;

CREATE DATABASE IF NOT EXISTS rrhhdb;

USE rrhhdb;

CREATE TABLE cities(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE addresses(
    id INT NOT NULL AUTO_INCREMENT,
    street VARCHAR(30) NOT NULL,
    number INT NOT NULL,
    city_id INT NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE departments(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE jobs(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    description VARCHAR(255),
    department_id INT NOT NULL,

    PRIMARY KEY(id)
);

CREATE TABLE employees(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address_id INT NOT NULL,
    birthdate DATE NOT NULL,
    hiredate DATE NOT NULL,
    boss_id INT,
    job_id INT NOT NULL,
    firedate DATE,

    PRIMARY KEY(id)
);

ALTER TABLE addresses ADD CONSTRAINT fk_address_city FOREIGN KEY(city_id) REFERENCES cities(id);
ALTER TABLE jobs ADD CONSTRAINT fk_job_department FOREIGN KEY(department_id) REFERENCES departments(id);
ALTER TABLE employees ADD CONSTRAINT fk_address_employee FOREIGN KEY(address_id) REFERENCES addresses(id);
ALTER TABLE employees ADD CONSTRAINT fk_boss_employee FOREIGN KEY(boss_id) REFERENCES employees(id) ON DELETE SET NULL;
ALTER TABLE employees ADD CONSTRAINT fk_job_employee FOREIGN KEY(job_id) REFERENCES jobs(id);

ALTER TABLE employees ADD CONSTRAINT uk_email_employee UNIQUE(email);

INSERT INTO cities (name) VALUES ('Tokio');
INSERT INTO cities (name) VALUES ('Nueva York');
INSERT INTO cities (name) VALUES ('Londres');
INSERT INTO cities (name) VALUES ('París');
INSERT INTO cities (name) VALUES ('Madrid');
INSERT INTO cities (name) VALUES ('Roma');
INSERT INTO cities (name) VALUES ('Santiago');
INSERT INTO cities (name) VALUES ('Ciudad de México');
INSERT INTO cities (name) VALUES ('Toronto');
INSERT INTO cities (name) VALUES ('Río de Janeiro');

INSERT INTO departments (name) VALUES ('Development');
INSERT INTO departments (name) VALUES ('Quality Assurance');
INSERT INTO departments (name) VALUES ('Technical Support');
INSERT INTO departments (name) VALUES ('Sales');

INSERT INTO jobs (name, description, department_id) VALUES ('Software Developer', 'Responsible for developing and maintaining software applications.', 1);
INSERT INTO jobs (name, description, department_id) VALUES ('QA Engineer', 'Ensures the quality of software products through testing and quality assurance processes.', 2);
INSERT INTO jobs (name, description, department_id) VALUES ('Technical Support Specialist', 'Provides technical assistance and support to clients and end-users.', 3);
INSERT INTO jobs (name, description, department_id) VALUES ('Sales Representative', 'Responsible for selling company products and services to clients.', 4);
INSERT INTO jobs (name, description, department_id) VALUES ('UI/UX Designer', 'Creates user-friendly and visually appealing user interfaces.', 1);
INSERT INTO jobs (name, description, department_id) VALUES ('Network Engineer', 'Designs and implements computer networks for the organization.', 3);
INSERT INTO jobs (name, description, department_id) VALUES ('Sales Manager', 'Leads and manages the sales team to achieve revenue goals.', 4);
INSERT INTO jobs (name, description, department_id) VALUES ('Software Architect', 'Designs high-level structures for software development projects.', 1);
INSERT INTO jobs (name, description, department_id) VALUES ('Customer Support Specialist', 'Provides support and assistance to customers regarding products or services.', 3);
INSERT INTO jobs (name, description, department_id) VALUES ('Marketing Analyst', 'Analyzes market trends and develops strategies to promote products.', 4);
INSERT INTO jobs (name, description, department_id) VALUES ('IT Security Analyst', 'Ensures the security of the organization information systems.', 2);
INSERT INTO jobs (name, description, department_id) VALUES ('DevOps Engineer', 'Manages the development and operations aspects of software projects.', 1);
INSERT INTO jobs (name, description, department_id) VALUES ('Business Analyst', 'Analyzes and documents business processes and requirements.', 2);

INSERT INTO addresses (street, number, city_id) VALUES ('Sunset Boulevard', 321, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('Ocean Drive', 654, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('Mountain View', 987, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('City Center', 111, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('Sunrise Avenue', 555, 1);
INSERT INTO addresses (street, number, city_id) VALUES ('Meadow Lane', 789, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('River Road', 222, 3);
INSERT INTO addresses (street, number, city_id) VALUES ('Greenwood Street', 444, 4);
INSERT INTO addresses (street, number, city_id) VALUES ('Highland Avenue', 987, 5);
INSERT INTO addresses (street, number, city_id) VALUES ('Lakeview Drive', 321, 6);
INSERT INTO addresses (street, number, city_id) VALUES ('Sunset Boulevard', 654, 7);
INSERT INTO addresses (street, number, city_id) VALUES ('Meadow Lane', 111, 8);
INSERT INTO addresses (street, number, city_id) VALUES ('Greenwood Street', 555, 9);
INSERT INTO addresses (street, number, city_id) VALUES ('River Road', 789, 10);
INSERT INTO addresses (street, number, city_id) VALUES ('Sunrise Avenue', 222, 1);
INSERT INTO addresses (street, number, city_id) VALUES ('Meadow Lane', 444, 2);
INSERT INTO addresses (street, number, city_id) VALUES ('Highland Avenue', 666, 3);
INSERT INTO addresses (street, number, city_id) VALUES ('Lakeview Drive', 888, 4);
INSERT INTO addresses (street, number, city_id) VALUES ('Hillside Lane', 888, 5);
INSERT INTO addresses (street, number, city_id) VALUES ('Pine Street', 777, 6);

INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('John', 'Smith', 'john.smith@example.com', '5551234', 1, '1980-01-15', '2022-01-01', NULL, 8);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Alice', 'Johnson', 'alice.johnson@example.com', '9875678', 2, '1975-05-20', '2022-01-02', NULL, 2);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Michael', 'Williams', 'michael.williams@example.com', '3338765', 3, '1970-10-10', '2022-01-03', NULL, 3);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Emma', 'Brown', 'emma.brown@example.com', '7774321', 4, '1978-11-28', '2022-01-04', NULL, 4);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Mark', 'Anderson', 'mark.anderson@example.com', '5555678', 5, '1992-03-22', '2022-01-11', 1, 5);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Sophia', 'Lee', 'sophia.lee@example.com', '9875432', 6, '1994-08-14', '2022-01-12', 2, 2);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Daniel', 'Rodriguez', 'daniel.rodriguez@example.com', '3339876', 7, '1990-12-05', '2022-01-13', 3, 6);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Olivia', 'Garcia', 'olivia.garcia@example.com', '7778765', 8, '1988-05-30', '2022-01-14', 4, 7);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Liam', 'Martinez', 'liam.martinez@example.com', '1111111', 9, '1991-06-18', '2022-01-15', 1, 1);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Ava', 'Hernandez', 'ava.hernandez@example.com', '2222222', 10, '1993-09-25', '2022-01-16', 2, 2);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Noah', 'Gomez', 'noah.gomez@example.com', '3333333', 11, '1989-02-10', '2022-01-17', 3, 3);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Isabella', 'Jones', 'isabella.jones@example.com', '4444444', 12, '1995-04-01', '2022-01-18', 4, 4);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Ethan', 'Moore', 'ethan.moore@example.com', '5555555', 13, '1987-07-21', '2022-01-19', 1, 8);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Olivia', 'Perez', 'olivia.perez@example.com', '6666666', 14, '1990-11-30', '2022-01-20', 2, 11);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Liam', 'Rivera', 'liam.rivera@example.com', '7777777', 15, '1994-01-15', '2022-01-21', 3, 9);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Emma', 'Miller', 'emma.miller@example.com', '8888888', 16, '1985-04-29', '2022-01-22', 4, 10);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Lucas', 'Martin', 'lucas.martin@example.com', '9999999', 17, '1992-08-06', '2022-01-23', 1, 1);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Mia', 'Sanchez', 'mia.sanchez@example.com', '1011010', 18, '1988-12-12', '2022-01-24', 2, 13);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Sophie', 'Gonzalez', 'sophie.gonzalez@example.com', '1112222', 19, '1989-05-12', '2022-01-25', 1, 5);
INSERT INTO employees (name, lastname, email, phone, address_id, birthdate, hiredate, boss_id, job_id) VALUES ('Jackson', 'Wang', 'jackson.wang@example.com', '3334444', 20, '1991-10-18', '2022-01-26', 2, 11);