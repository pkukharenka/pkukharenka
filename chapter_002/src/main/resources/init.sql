DROP TABLE IF EXISTS item, comment CASCADE;

CREATE TABLE IF NOT EXISTS item (
  id serial primary key,
  item_name varchar(2000) not null,
  description text,
  create_date timestamp not null default now()
);

CREATE TABLE IF NOT EXISTS comment (
  id SERIAL PRIMARY KEY,
  comment text,
  create_date TIMESTAMP NOT NULL DEFAULT now(),
  item_id integer REFERENCES item(id)
);