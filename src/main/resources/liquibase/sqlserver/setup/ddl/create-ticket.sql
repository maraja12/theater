CREATE TABLE ticket(
    id bigint not null,
    invoice_id bigint not null,
    scene varchar(100) not null,
    place varchar(100) not null,
    date_time DATETIME not null,
    price decimal(18,2) not null,
    discount decimal(18,2),
    show_id bigint not null,
    PRIMARY KEY(id, invoice_id),
    CONSTRAINT invoice_fk FOREIGN KEY (invoice_id) REFERENCES invoice(id),
    CONSTRAINT show_fk2 FOREIGN KEY (show_id) REFERENCES show(id)
)