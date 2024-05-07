DROP TABLE IF EXISTS users;

CREATE TABLE users (
 id int unsigned AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL,
 email VARCHAR(255) NOT NULL UNIQUE,
 PRIMARY KEY(id)
);

INSERT INTO users (name,email) VALUES ('yamada', 'yamada@example.com');
INSERT INTO users (name,email) VALUES ('kobashi', 'kobashi@example.com');
INSERT INTO users (name,email) VALUES ('akiyama', 'akiyama@example.com');
INSERT INTO users (name,email) VALUES ('misawa', 'misawa@example.com');
INSERT INTO users (name,email) VALUES ('takayama', 'takayama@example.com');
