DROP TABLE IF EXISTS department;
CREATE TABLE department (
                            department_id decimal(4,0) NOT NULL AUTO_INCREMENT,
                            department_name varchar(14) DEFAULT NULL,
                            PRIMARY KEY (department_id)
);

DROP TABLE IF EXISTS employee;
CREATE TABLE employee (
                          employee_id decimal(4,0) NOT NULL AUTO_INCREMENT,
                          department_id decimal(4,0),
                          name varchar(64) NOT NULL,
                          role varchar(32) DEFAULT NULL,
                          PRIMARY KEY (employee_id)
);

ALTER TABLE employee ADD FOREIGN KEY (department_id) REFERENCES department (department_id);
