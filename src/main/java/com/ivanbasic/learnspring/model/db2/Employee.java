package com.ivanbasic.learnspring.model.db2;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "schema2", name = "employees")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "hire_date")
    private LocalDateTime hireDate;

    @Column(name = "username")
    private String username;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    Department department;


    public Employee() {}

    public Employee(int employeeId, String firstName, LocalDateTime hireDate, Department department) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;

        this.department = department;
    }

    // Getters
    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public String getUsername() {
        return username;
    }

    public Department getDepartment() {
        return department;
    }
}