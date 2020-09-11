DROP TABLE IF EXISTS employee_data;

CREATE TABLE employee_data (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  date_of_birth VARCHAR(250) NOT NULL,
  address_Line_1 VARCHAR(250),
  address_Line_2 VARCHAR(250),
  city VARCHAR(250),
  state VARCHAR(2),
  country VARCHAR(250),
  zip_code VARCHAR(250)
);