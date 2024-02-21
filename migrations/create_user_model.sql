create table Users
(
    id serial primary key,
   name varchar(60),
   email varchar(60),
   pass varchar(100)
);

alter table Users add column phone varchar(32);
alter table Users add column about text;