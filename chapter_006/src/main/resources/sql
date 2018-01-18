drop table if exists roles, users, rules, role_rule, state, category, item, attach, comments cascade;

create table roles (
	id serial primary key,
	role_name varchar(250)
);

create table users (
	id serial primary key,
	user_name varchar(250),
	create_date timestamp not null default now(),
	role_id integer not null references roles(id)
);

create table rules (
	id serial primary key,
	rule_name varchar(500)
);

create table role_rule(
	role_id integer references roles(id),
	rule_id integer references rules(id)
);
create table state (
	id serial primary key,
	state_name varchar(500)
);

create table category (
	id serial primary key,
	cat_name varchar(500)
);

create table item(
	id serial primary key,
	theme varchar(1000),
	description text,
	create_date timestamp not null default now(),
	state_id integer references state(id),
	category_id integer references category(id)
);


create table attach (
	id serial primary key,
	file_path varchar(500),
	item_id integer references item(id)
);

create table comments (
	id serial primary key,
	conten text,
	create_date timestamp,
	item_id integer references item(id)
);

insert into roles (id, role_name) values (1, 'admin');
insert into roles (id, role_name) values (2, 'user');

insert into rules (id, rule_name) values (1, 'Создание заявки');
insert into rules (id, rule_name) values (2, 'Добавление заявки');
insert into rules (id, rule_name) values (3, 'Удаление заявки');
insert into rules (id, rule_name) values (4, 'Обновление заявки');
insert into rules (id, rule_name) values (5, 'Удаление пользователей');

insert into role_rule(role_id, rule_id) values (1, 1);
insert into role_rule(role_id, rule_id) values (1, 2);
insert into role_rule(role_id, rule_id) values (1, 3);
insert into role_rule(role_id, rule_id) values (1, 4);
insert into role_rule(role_id, rule_id) values (1, 5);

insert into role_rule(role_id, rule_id) values (2, 1);
insert into role_rule(role_id, rule_id) values (2, 2);
insert into role_rule(role_id, rule_id) values (2, 3);
insert into role_rule(role_id, rule_id) values (2, 4);

insert into users(user_name, role_id) values ('Pyotr', 1);
insert into users(user_name, role_id) values ('Ivan', 2);

insert into state(id, state_name) values (1, 'В процессе');
insert into state(id, state_name) values (2, 'Закрыта');