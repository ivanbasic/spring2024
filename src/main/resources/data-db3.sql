DELETE FROM authorities;
DELETE FROM users;

--noop
INSERT INTO users (username, password, enabled)
VALUES (
    'ivan',
    '{noop}i',
    true
);
INSERT INTO authorities (username, authority)
VALUES
  ('ivan', 'read'),
  ('ivan', 'ADMIN'),
  ('ivan', 'ROLE_JOKER');


-- bcrypt
INSERT INTO users (username, password, enabled)
VALUES (
  'bcryptuser',
  '{bcrypt}$2a$10$/Z/Wdduj6hzFm1.CQ8Tb1uF5N.GR6yzj9ApkLkKtO8WjsZqDLJYuO',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('bcryptuser', 'read');

-- argon2
INSERT INTO users (username, password, enabled)
VALUES (
  'argon2user',
  '{argon2}$argon2id$v=19$m=16384,t=2,p=1$mDSm610T5WJxX8YLhHONrQ$L0SOj1Pb1+LYwWQHMZrQsHHiekRNNuGC3xtJ2lRMOLk',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('argon2user', 'read');

-- pbkdf2
INSERT INTO users (username, password, enabled)
VALUES (
  'pbkdf2user',
  '{pbkdf2}6d8f9d6bf7e5a4feeb8f3afd5f34c4ebf1c3ad398846cb07433cc58c71fb894c17c5e2866bc49c8b',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('pbkdf2user', 'read');

-- sha256
INSERT INTO users (username, password, enabled)
VALUES (
  'sha256user',
  '{sha256}8cc2159578e32cb31f69a8d1e60b27315d43b526d7904a02c1c1995847167342933083a57cfd6465',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('sha256user', 'read');

-- ldap
INSERT INTO users (username, password, enabled)
VALUES (
  'ldapuser',
  '{ldap}{SSHA}ydE1nZjR9H51TubBwOYmbH/jhi+twyHvZKHUbg==',
  true
);
INSERT INTO authorities (username, authority)
VALUES ('ldapuser', 'read');