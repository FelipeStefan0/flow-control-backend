create table if not exists months(
   id serial primary key not null,
   name varchar not null
);

create table if not exists report(
    id serial primary key not null,
    month integer not null references months,
    year integer not null,
    total_value float not null,
    in_total_value float not null,
    out_total_value float not null
);