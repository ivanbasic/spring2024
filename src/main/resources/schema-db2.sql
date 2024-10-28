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


-- Oracle HR schema, adapted for postgres
DROP TABLE IF EXISTS schema2.employees;
CREATE TABLE IF NOT EXISTS schema2.employees
   ( employee_id NUMERIC(6)
   , first_name VARCHAR(20)
   , last_name VARCHAR(25)
   , email VARCHAR(25)
   , phone_number VARCHAR(20)
   , hire_date TIMESTAMP
   , job_id VARCHAR(10)
   , salary NUMERIC(8,2)
   , commission_pct NUMERIC(2,2)
   , manager_id NUMERIC(6)
   , department_id NUMERIC(4)
   ) ;

DROP TABLE IF EXISTS schema2.departments;
CREATE TABLE IF NOT EXISTS schema2.departments
   ( department_id NUMERIC(4)
   , department_name VARCHAR(30)
   , manager_id NUMERIC(6)
   , location_id NUMERIC(4)
   ) ;