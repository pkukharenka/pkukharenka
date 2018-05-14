--импорт справочных данных о брэндах и моделях авто.
COPY model FROM 'E:/model.csv' DELIMITER ',' CSV;

--импорт опций и характеристик автомобилей.
COPY option FROM 'E:/option.csv' DELIMITER ',' CSV ENCODING 'windows-1251';

INSERT INTO roles (id, role_type) VALUES (1, 'Администратор');

INSERT INTO users (id, email, user_name, password, role_id)  VALUES (1, 'admin', 'admin', '123', 1);

