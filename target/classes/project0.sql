-- project 0 db editor

CREATE USER revabank_app
WITH PASSWORD 'revabank';

grant all privileges
on database postgres
to revabank_app;

create table user_roles(
	id serial,
	name varchar(25) not null,
	
	constraint user_roles_pk 
	primary key (id)
);

create table app_users(
	id serial,
	username varchar(25) unique not null,
	password varchar(256) not null,
	first_name varchar(25) not null,
	last_name varchar(25) not null,
	email varchar(256) unique not null,
	role_id int not null,
	
	constraint app_users_pk 
	primary key (id),
	
	constraint user_role_fk 
	foreign key (role_id)
	references user_roles
);

-- inserting a single value into a table
insert into user_roles (name)
values ('Admin');

-- query the user_roles table to ensure the value was placed within it
select * from user_roles;

-- insert multiple values into a table with a single insert statement
insert into user_roles (name)
values ('Premium Member'), ('Basic Member'), ('Locked');

-- populate app_users table with some dummy data
insert into app_users (username, password, first_name, last_name, email, role_id)
values 
	('aanderson', 'password', 'Alice', 'Anderson', 'aanderson@revature.com', 1),
	('bbailey', 'password', 'Bob', 'Bailey', 'bbailey@revature.com', 2),
	('ccalhoun', 'password', 'Charles', 'Calhoun', 'ccalhoun@revature.com', 3),
	('ddavis', 'password', 'Daniel', 'Davis', 'ddavis@revature.com', 4);

-- notice that auto-completion includes a "table alias"; quick reference to this table
-- that can be used in other places in the same SQL statement
select * from app_users au; -- alias here is not useful for such a simple statement

-- find the user in app_users with an id of 4 using a WHERE clause
select * from app_users
where id = 4;

-- find users whose email ends with "n@revature.com" using the LIKE operator
-- notice the use of the %, which is the wildcard character
select * from app_users
where email like '%n@revature.com';

-- get all users and present them in order by id
select * from app_users
order by id asc; -- asc is implicit and not required

-- get all users and present them in reverse order by id
select * from app_users
order by id desc;

-- grab all column data from both the app_users and user_roles table in a single query
select * 
from app_users au
join user_roles ur
on au.role_id = ur.id;

-- grab only the username and password from the app_users table
select username, password 
from app_users;

-- grab only the username and password from the app_users table, and the role name from user_roles
-- no aliases used
select app_users.username, app_users.password, user_roles.name
from app_users
join user_roles
on app_users.role_id = user_roles.id;

-- same query, using aliases
select au.username, au.password, ur.name
from app_users au
join user_roles ur
on au.role_id = ur.id;


-- test query
select * from revabank.app_users
where username = 'aanderson' and password = 'password';
