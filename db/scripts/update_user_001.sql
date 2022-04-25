CREATE TABLE users
(
    id       serial primary key,
    email    text,
    password text
);

ALTER TABLE users
    ADD CONSTRAINT email_unique UNIQUE (email)