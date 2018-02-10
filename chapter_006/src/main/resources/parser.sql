CREATE TABLE IF NOT EXISTS offer (
  id SERIAL PRIMARY KEY,
  title VARCHAR(2000) not NULL,
  content text,
  create_date DATE,
  url VARCHAR(2000)
);