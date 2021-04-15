create table if not exists USERS (
	id serial primary key,
	username varchar(25) unique not null,
	user_password varchar(25) not null,
	email varchar(320) unique not null,
	name varchar(100) not null
);

create table if not exists USER_RECIPE_LIST (
	id serial,
	id_user int references USERS(id),
	list_name varchar(25) not null,
	description text,
	visibility varchar(7) check (visibility in ('public', 'private')) default 'private',
	primary key(id, id_user)
);

create table if not exists RECIPE (
	id serial primary key,
	id_api int unique,
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

create table if not exists USERS_RECIPES (
	id_recipes int references RECIPE(id),
	id_user int,
	id_user_recipe_list int,
	foreign key (id_user, id_user_recipe_list) references USER_RECIPE_LIST(id_user, id),
	primary key(id_recipes, id_user, id_user_recipe_list)
);

create table if not exists INGREDIENT_DETAILS (
	id serial primary key,
	id_api int,
	id_recipe int references RECIPE(id),
	aisle varchar(30) not null,
	ingredient_name varchar(50) not null,
	amount double precision not null,
	unit varchar(20) not null,
	image text
);