CREATE SCHEMA it_schema;
CREATE TABLE it_schema.domain
(
    id          UUID         NOT NULL,
    context     varchar(255) NOT NULL,
    sub_context varchar(255) NOT NULL,
    name        varchar(255) NOT NULL,
    constraint pk_domain primary key (id),
    constraint uk_domain unique (context, sub_context, name)
);

CREATE TABLE it_schema.domain_value
(
    surrogate_key UUID NOT NULL,
    domain_id     UUID NOT NULL,
    id            integer,
    key           varchar(255),
    constraint pk_domain_value primary key (surrogate_key),
    constraint fk_domain_id foreign key (domain_id) references it_schema.domain (id),
    constraint uk_domain_key unique (domain_id, id, key)
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO it_schema.domain (id, context, sub_context, name)
VALUES (uuid_generate_v4(), 'default', 'default', 'action_type'),
       (uuid_generate_v4(), 'admin', 'default', 'action_type'),
       (uuid_generate_v4(), 'customer', 'default', 'action_type'),
       (uuid_generate_v4(), 'admin', 'partner', 'action_type');

INSERT INTO it_schema.domain_value (surrogate_key, domain_id, key)
VALUES (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'create'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'pause'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'update'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'store'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'cancel'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'default'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'delete');

INSERT INTO it_schema.domain_value (surrogate_key, domain_id, key)
VALUES (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'create'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'pause'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'update'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'store'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'cancel'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'delete');

INSERT INTO it_schema.domain_value (surrogate_key, domain_id, key)
VALUES (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'partner'
           AND name = 'action_type'),
        'create'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'partner'
           AND name = 'action_type'),
        'pause'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'partner'
           AND name = 'action_type'),
        'update'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'admin'
           AND d.sub_context = 'partner'
           AND name = 'action_type'),
        'store');

INSERT INTO it_schema.domain_value (surrogate_key, domain_id, key)
VALUES (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'customer'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'create'),
       (uuid_generate_v4(),
        (SELECT d.id
         FROM it_schema.domain d
         WHERE d.context = 'customer'
           AND d.sub_context = 'default'
           AND name = 'action_type'),
        'cancel');
