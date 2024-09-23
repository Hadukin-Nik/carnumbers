alter database carnumbers owner to admin;

\connect carnumbers

create table carcodes (
    id bigserial primary key,
    letters varchar(6)
);

alter table carcodes owner to admin;