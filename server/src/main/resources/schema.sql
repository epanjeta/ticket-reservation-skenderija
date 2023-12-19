-- drop and create schema
drop schema ppis cascade;
create schema ppis;

create table ppis.location
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

create table ppis.user
(
    id        SERIAL PRIMARY KEY,
    email     VARCHAR(50) UNIQUE NOT NULL,
    password  VARCHAR(100)       NOT NULL,
    user_type VARCHAR(50)        NOT NULL,
    name      VARCHAR(50),
    code      VARCHAR(6),
    verified  BOOLEAN            NOT NULL DEFAULT FALSE,
    location INT,
    FOREIGN KEY (location)
        REFERENCES ppis.location (id)
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

create table ppis.ticket
(
    id SERIAL PRIMARY KEY,
    status VARCHAR(50) NOT NULL,
    event INT NOT NULL,
    type INT NOT NULL,
    userId INT NOT NULL,
    location INT,
    FOREIGN KEY (location)
        REFERENCES ppis.location (id),
    FOREIGN KEY (event)
        REFERENCES ppis.event (id),
    FOREIGN KEY (type)
        REFERENCES ppis.ticket_type (id),
    FOREIGN KEY (userId)
        REFERENCES ppis.user(id)

);

CREATE TABLE ppis.task
(
    id SERIAL PRIMARY KEY ,
    location INT NOT NULL ,
    ticket INT NOT NULL,
    status VARCHAR(50) NOT NULL,
    FOREIGN KEY (location) REFERENCES ppis.location (id),
    FOREIGN KEY (ticket) REFERENCES ppis.ticket (id)
);

CREATE TABLE ppis.jwt
(
    id SERIAL PRIMARY KEY,
    token VARCHAR(1000) NOT NULL,
    valid BOOLEAN NOT NULL DEFAULT TRUE
);
