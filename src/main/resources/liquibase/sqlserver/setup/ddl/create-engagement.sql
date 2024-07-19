CREATE TABLE engagement(
    actor_id bigint not null,
    show_id bigint not null,
    role varchar(100) not null,
    start_date date not null,
    end_date date,
    primary key (actor_id, show_id),
    CONSTRAINT actor_fk FOREIGN KEY (actor_id) REFERENCES actor(id),
    CONSTRAINT show_fk FOREIGN KEY (show_id) REFERENCES show(id)
)