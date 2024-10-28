package com.ivanbasic.learnspring.db2.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "schema2", name = "employees")
public class Employees {
    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "hire_date")
    private LocalDateTime hireDate;


    @ManyToOne()
    @JoinColumn(name = "department_id")
    Departments departments;


    public Employees() {}

    public Employees(int employeeId, String firstName, LocalDateTime hireDate, Departments departments) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;

        this.departments = departments;
    }

}
