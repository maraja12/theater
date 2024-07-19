CREATE TABLE show(
    id bigint not null identity(1,1),
    name varchar(100) not null,
    duration int not null,
    genre varchar(100),
    director_id bigint,
    writer_id bigint,
    primary key (id),
    constraint director_fk foreign key (director_id) references director(id),
    constraint writer_fk foreign key (writer_id) references writer(id)
)