create table customer(
    id bigint not null identity(1,1),
    email varchar(100) not null,
    password varchar(8) not null,
    name varchar(100) not null,
    surname varchar(100) not null,
    age int default 16,
    primary key(id)
)