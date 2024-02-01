create schema if not exists actions;

create table if not exists actions.types (
    id serial primary key not null,
    name varchar not null
);

create table if not exists actions.action (
    id serial primary key not null,
    types int references actions.types,
    amount float not null,
    hours timestamp with time zone not null,
    notes varchar
);
