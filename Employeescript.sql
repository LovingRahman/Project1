drop table if exists employees;
drop table if exists tickets;
create table employees(id serial primary key, username varchar(50) unique, password varchar(50), manager bool);
create table tickets(id serial primary key, employeeusername varchar(50) references employees(username), amount decimal(30,2), description varchar(50), status varchar(50));

insert into employees values (default, 'Bob','1234', false);
insert into tickets values (default, 'Bob', 988.24, 'Room Service', 'unapproved');


