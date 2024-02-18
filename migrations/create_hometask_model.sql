create table Hometasks (
    id BIGSERIAL PRIMARY KEY,
    name varchar(60),
    description text,
    content text,
    maxPoints int default 0,
    moduleId INTEGER REFERENCES Modules (Id)
);