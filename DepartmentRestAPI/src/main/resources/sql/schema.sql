-- -----------------------------------------------------
-- Table `departments`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS department (
  dep_id INT(11) NOT NULL AUTO_INCREMENT,
  dep_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (dep_id));



-- -----------------------------------------------------
-- Table `departments`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS employee (
  emp_id INT(11) NOT NULL AUTO_INCREMENT,
  secondname VARCHAR(55) NOT NULL,
  firstname VARCHAR(55) NOT NULL,
  patronymic VARCHAR(55) NOT NULL,
  birthdate DATE NOT NULL,
  salary DOUBLE NOT NULL,
  department_dep_id INT(11) NOT NULL,
  PRIMARY KEY (emp_id),
  CONSTRAINT fk_employee_department
    FOREIGN KEY (department_dep_id)
    REFERENCES department (dep_id)
    ON DELETE CASCADE);
