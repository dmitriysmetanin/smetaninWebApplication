create table Courses
(
    id BIGSERIAL PRIMARY KEY,
   name varchar(60),
   description text
);

alter table Courses add column authorId INTEGER REFERENCES users (Id);