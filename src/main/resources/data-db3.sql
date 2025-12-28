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


INSERT INTO users (username, password, enabled)
VALUES (
  'bcryptuser',
  '{bcrypt}$2a$10$/Z/Wdduj6hzFm1.CQ8Tb1uF5N.GR6yzj9ApkLkKtO8WjsZqDLJYuO',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('bcryptuser', 'read');