-- drop and create schema
drop schema ppis cascade;
create schema ppis;

create table ppis.user
(
    id              SERIAL PRIMARY KEY,
    email           VARCHAR(50) UNIQUE NOT NULL,
    password        VARCHAR(100)       NOT NULL,
    user_type       VARCHAR(50)        NOT NULL,
    name    VARCHAR(50),
    code            VARCHAR(6),
    verified        BOOLEAN            NOT NULL DEFAULT FALSE
);