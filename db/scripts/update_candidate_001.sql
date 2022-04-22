CREATE TABLE post
(
    id          serial primary key,
    name        text,
    description text,
    created     date,
    photo       bytea
);