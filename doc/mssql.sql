CREATE TABLE Manager(
  id int NOT NULL identity(1,1),
  age int DEFAULT NULL,
  nice_name varchar(32) DEFAULT NULL,
  user_name varchar(32) DEFAULT NULL,
  PRIMARY KEY (id)
) 

