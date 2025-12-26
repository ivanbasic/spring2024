DELETE FROM authorities;
DELETE FROM users;

INSERT INTO users (username, password, enabled)
VALUES (
    'ivan',
    '{noop}i',
    true
);

INSERT INTO authorities (username, authority)
VALUES (
    'ivan',
    'read'
);