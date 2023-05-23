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
