DROP TABLE IF EXISTS city, country, role, users CASCADE ;

CREATE TABLE IF NOT EXISTS country (
  id           SERIAL PRIMARY KEY,
  country_name VARCHAR(300)
);

CREATE TABLE IF NOT EXISTS city (
  id         SERIAL PRIMARY KEY,
  city_name  VARCHAR(300),
  country_id INTEGER NOT NULL REFERENCES country (id)
);

CREATE TABLE IF NOT EXISTS role (
  id   SERIAL PRIMARY KEY,
  type VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS users (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(255),
  login       VARCHAR(255),
  email       VARCHAR(255),
  create_date DATE,
  password    VARCHAR(255),
  role_id     INTEGER,
  country_id  INTEGER,
  city_id     INTEGER
);

INSERT INTO country (id, country_name) VALUES (1, 'Беларусь');
INSERT INTO country (id, country_name) VALUES (2, 'Россия');
INSERT INTO country (id, country_name) VALUES (3, 'Польша');
INSERT INTO city (id, city_name, country_id) VALUES (1, 'Минск', 1);
INSERT INTO city (id, city_name, country_id) VALUES (2, 'Гродно', 1);
INSERT INTO city (id, city_name, country_id) VALUES (3, 'Могилев', 1);
INSERT INTO city (id, city_name, country_id) VALUES (4, 'Стобцы', 1);
INSERT INTO city (id, city_name, country_id) VALUES (5, 'Москва', 2);
INSERT INTO city (id, city_name, country_id) VALUES (6, 'Тюмень', 2);
INSERT INTO city (id, city_name, country_id) VALUES (7, 'Волгоград', 2);
INSERT INTO city (id, city_name, country_id) VALUES (8, 'Варшава', 3);
INSERT INTO city (id, city_name, country_id) VALUES (9, 'Гданьск', 3);
INSERT INTO role (id, type) VALUES (1, 'Администратор');
INSERT INTO role (id, type) VALUES (2, 'Пользователь');
INSERT INTO users (id, name, login, email, create_date, password, role_id, country_id, city_id) VALUES
  (1, 'admin', 'admin', 'admin', '2018-03-20', '1', 1, 1, 1);
