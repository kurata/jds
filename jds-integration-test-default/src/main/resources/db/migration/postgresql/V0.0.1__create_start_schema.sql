CREATE TABLE domain
(
    id          UUID         NOT NULL,
    context     varchar(255) NOT NULL,
    sub_context varchar(255) NOT NULL,
    name        varchar(255) NOT NULL,
    constraint pk_domain primary key (id),
    constraint uk_domain unique (context, sub_context, name)
);

CREATE TABLE domain_value
(
    surrogate_key UUID NOT NULL,
    domain_id     UUID NOT NULL,
    id            integer,
    key           varchar(255),
    constraint pk_domain_value primary key (surrogate_key),
    constraint fk_domain_id foreign key (domain_id) references domain (id),
    constraint uk_domain_key unique (domain_id, id, key)
);
