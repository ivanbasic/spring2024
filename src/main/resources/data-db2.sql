
INSERT INTO schema2.departments (department_id,department_name)  VALUES (1,'IT');
INSERT INTO schema2.departments (department_id,department_name)  VALUES (2,'HR');

INSERT INTO schema2.employees (employee_id,first_name, hire_date, department_id)
       VALUES (1, 'Ada', '2015-11-21 14:30:15',1);

INSERT INTO schema2.employees (employee_id,first_name, hire_date, department_id)
       VALUES (2, 'Niklaus', '2016-11-21 14:30:15',1);

INSERT INTO schema2.employees (employee_id,first_name, hire_date, department_id)
       VALUES (2, 'Karl', '2016-11-21 14:30:15',2);