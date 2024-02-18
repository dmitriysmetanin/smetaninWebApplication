create table SubmittedHometasks (
    id BIGSERIAL PRIMARY KEY,
    status int default 0,
    file varchar(256),
    points int default 0,
    teachersComment text,
    hometaskId INTEGER REFERENCES Modules (Id),
    userId INTEGER REFERENCES Users (Id)
);

alter table SubmittedHometasks add constraint uniqueHometaskIdUserId UNIQUE (hometaskId, userId);