CREATE TABLE domain_value
(
    id          CHAR(36)     NOT NULL PRIMARY KEY,
    context     varchar(255) NOT NULL,
    sub_context varchar(255) NOT NULL,
    name        varchar(255) NOT NULL
);
