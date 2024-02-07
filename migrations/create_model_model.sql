create table Modules
(
    id BIGSERIAL PRIMARY KEY,
    name varchar(60),
    description text,
    content text,
    courseId INTEGER REFERENCES Courses (Id)
);