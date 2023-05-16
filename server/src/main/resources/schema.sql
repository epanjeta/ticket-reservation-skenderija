-- drop and create schema
drop schema ppis cascade;
create schema ppis;

create table ppis.user
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(50) UNIQUE NOT NULL,
    password  VARCHAR(100)       NOT NULL,
    user_type VARCHAR(50)        NOT NULL,
    name      VARCHAR(50),
    code      VARCHAR(6),
    verified  BOOLEAN            NOT NULL DEFAULT FALSE
);

create table ppis.image
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(50),
    type      VARCHAR(50),
    imagedata bytea
);

create table ppis.event
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(200)  NOT NULL,
    type        VARCHAR(50)   NOT NULL,
    description VARCHAR(1000) NOT NULL,
    date        TIMESTAMP     NOT NULL,
    picture     INT           NOT NULL,
    FOREIGN KEY (picture)
        REFERENCES ppis.image (id)
);

create table ppis.ticket_type
(
    id SERIAL PRIMARY KEY,
    ticket_type VARCHAR(200)  NOT NULL,
    ticket_price  VARCHAR(200)  NOT NULL
);

create table ppis.availabletickets
(
    id SERIAL PRIMARY KEY,
    event INT,
    ticket_type INT,
    available_tickets INT NOT NULL DEFAULT 0,
    total_tickets INT NOT NULL DEFAULT 0,
    FOREIGN KEY (event)
        REFERENCES ppis.event (id),
    FOREIGN KEY (ticket_type)
        REFERENCES ppis.ticket_type (id)
);
