alter database carnumbers owner to admin;

\connect carnumbers

create table carcodes (
    id bigserial primary key,
    letters varchar(6) unique,
    date date
);

alter table carcodes owner to admin;