package com.ivanbasic.learnspring.db2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( schema = "schema2", name="departments")
public class Department {

    @Id
    @Column(name = "department_id" )
    private int departmentId;

    @Column(name = "department_name" )
    private String departmentName;

    public Department() {}
    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

}
