CREATE TABLE invoice(
    id bigint not null identity(1,1),
    date DATE not null,
    status varchar(100),
    customer_id bigint,
    primary key(id),
    constraint customer_fk FOREIGN KEY (customer_id) REFERENCES customer(id)
)