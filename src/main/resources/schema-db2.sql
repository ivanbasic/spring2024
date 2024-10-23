SELECT 1;
CREATE SCHEMA IF NOT EXISTS schema2;
CREATE TABLE IF NOT EXISTS schema2.table2
    (
    id bigint,
    name varchar(255) ,
    email varchar(255),
    age bigint,
    CONSTRAINT table2_pkey PRIMARY KEY (id)
    );