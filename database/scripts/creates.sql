create schema if not exists public authorization postgres;

create table if not exists USERS (
	id_user serial primary key,
	username varchar(25) unique not null,
	password varchar(320) not null,
	email varchar(320) unique not null,
	name varchar(100) not null,
	ingredients text[]
);

create table if not exists USER_RECIPE_LIST (
	id_url serial,
	id_user int references USERS(id_user) on delete cascade,
	list_name varchar(25) not null,
	description text,
	visibility varchar(7) check (visibility in ('public', 'private')) default 'private',
	primary key(id_url, id_user)
);

create table if not exists RECIPE (
	id_recipe serial primary key,
	id_api int,
	id_user int,
	id_url int,
	foreign key (id_user, id_url) references USER_RECIPE_LIST(id_user, id_url) on delete cascade,
	recipe_name varchar(100) not null,
	ready_in_minutes smallint not null,
	instructions text not null,
	image text,
	servings smallint not null,
	dairy_free boolean not null,
	gluten_free boolean not null,
	vegan boolean not null,
	vegetarian boolean not null
);

create table if not exists INGREDIENT_DETAILS (
	id_ingredient serial primary key,
	id_api int,
	id_recipe int references RECIPE(id_recipe) on delete cascade,
	aisle varchar(30),
	ingredient_name varchar(50) not null,
	amount double precision not null,
	unit varchar(20) not null,
	image text
);