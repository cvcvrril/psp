CREATE TABLE IF not exists users (
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  PRIMARY KEY (id)
);
