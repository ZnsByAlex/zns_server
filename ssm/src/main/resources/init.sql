DROP TABLE IF EXISTS t_user;  
  
CREATE TABLE t_user (  
  id INT(11) NOT NULL AUTO_INCREMENT,  
  user_name VARCHAR(40) NOT NULL,  
  password VARCHAR(255) NOT NULL,  
  age INT(4) NOT NULL,  
  PRIMARY KEY (id)  
) ENGINE=INNODB DEFAULT CHARSET=utf8;  
  
INSERT  INTO t_user(user_name,password,age) VALUES ('XXX','sfasgfaf',24);  