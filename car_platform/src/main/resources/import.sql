--импорт справочных данных о брэндах и моделях авто.
COPY model FROM 'model.csv' DELIMITER ',' CSV;

--импорт опций и характеристик автомобилей.
COPY option FROM 'option.csv' DELIMITER ',' CSV ENCODING 'windows-1251';

INSERT INTO roles (role_type) VALUES ('Администратор');

INSERT INTO users (email, user_name, password, role_id)  VALUES ('admin', 'admin', '123', 1);

