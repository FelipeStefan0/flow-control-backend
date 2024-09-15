create schema if not exists models;
create schema if not exists registers;

create table if not exists models.types (
   id serial primary key not null,
   name varchar not null
);

create table if not exists registers.actions (
    id serial primary key not null,
    type int references models.types,
    value float not null,
    date timestamp with time zone not null,
    description varchar
);
