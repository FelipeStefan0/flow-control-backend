create table if not exists model.months(
   id serial primary key not null,
   name varchar not null
);

create schema if not exists report;

create table if not exists report.report(
    id serial primary key not null,
    month integer not null references model.months,
    year integer not null,
    total_value float not null,
    in_total_value float not null,
    out_total_value float not null
);