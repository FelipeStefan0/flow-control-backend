create table if not exists types (
   id serial primary key not null,
   name varchar not null
);

create table if not exists actions (
    id serial primary key not null,
    type int references types,
    value float not null,
    date timestamp with time zone not null,
    description varchar
);
