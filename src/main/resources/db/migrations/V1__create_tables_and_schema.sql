create schema if not exists actions;

create schema if not exists model;

create table if not exists model.types (
   id serial primary key not null,
   name varchar not null
);

create table if not exists actions.action (
    id serial primary key not null,
    type int references model.types,
    value float not null,
    date timestamp with time zone not null,
    description varchar
);
