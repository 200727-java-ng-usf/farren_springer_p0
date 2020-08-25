-- project 0 db editor

CREATE USER revabank_app
WITH PASSWORD 'revabank';

grant all privileges
on database postgres
to revabank_app;

drop table accounts;
drop table app_users;
drop table user_roles;

-- create table for roles
create table user_roles(
	id serial,
	name varchar(25) not null,

	constraint user_roles_pk
	primary key (id)
);

-- create table for users
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

-- create a table to hold accounts
create table accounts(
	id 				serial,
	account_type 	varchar(25) not null,
	balance 		numeric(20,2)

	constraint accounts_pk
	primary key (id)
);

alter table accounts
add user_id 		int;


-- inserting a single value into a table
insert into user_roles (name)
values ('Admin');

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

-- grab all column data from both the app_users and user_roles table in a single query
select *
from app_users au
join user_roles ur
on au.role_id = ur.id;

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

--UPDATE accounts
--SET balance = 200.00
--WHERE id = 27;

delete from accounts
where user_id is null;
--


drop table account_history;

CREATE TABLE account_history(
    id 				serial primary key,
	user_id 		text NOT NULL default session_user,
	operation 		varchar(25),
	account_id 		int,
	account_type 	varchar(25),
	balance 		numeric(15,2),
	created_at 		timestamp with time zone default current_timestamp
);

-- testing that this works for PreparedStatement object in service layer
--UPDATE project0.app_users SET email = 'aawest@gmail.com' WHERE id = 4;

create or replace function account_audit()
returns trigger
as $$

	begin

		IF TG_OP = 'INSERT' THEN
  			INSERT INTO account_history (operation, account_id, account_type, balance)
  			VALUES (TG_OP, NEW.*);
  		RETURN NEW;
--		ELSIF TG_OP = 'UPDATE' THEN
--   			INSERT INTO account_history (operation, account_id, account_type, balance)
--   			values (TG_OP, NEW.*);
--   		RETURN NEW;
--		ELSIF TG_OP = 'DELETE' THEN
--   			INSERT INTO account_history (operation, account_id, account_type, balance)
--   			values (TG_OP, OLD.*);
--   		RETURN OLD;
		END IF;

	end

$$ language plpgsql;

drop trigger log on accounts;

-- causes sql error. Does not do anything for delete or update
--create trigger log
--after insert on accounts
--for each row
--execute function account_audit();
--
--UPDATE project0.app_users SET email = 'aawest@gmail.com' WHERE id = 4;







