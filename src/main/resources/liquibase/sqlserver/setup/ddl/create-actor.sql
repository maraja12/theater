create table actor(
    id bigint not null identity(1,1),
    name varchar(100) not null,
    surname varchar(100) not null,
    primary key(id)
)