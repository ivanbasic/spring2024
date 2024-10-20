SELECT 1;
CREATE SCHEMA IF NOT EXISTS schema1;
CREATE TABLE IF NOT EXISTS schema1.customer (id bigint not null, first_name varchar(255), last_name varchar(255), primary key (id));