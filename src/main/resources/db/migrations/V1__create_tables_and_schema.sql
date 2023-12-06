create schema if not exists actions;

create table if not exists actions.types (
    id serial primary key not null,
    name varchar not null
);

create table if not exists actions.action (
    id serial primary key not null,
    types_action int not null references actions.types,
    value_action float not null,
    date_action varchar not null,
    notes varchar
);
