package com.ivanbasic.learnspring.db2.model;
import jakarta.persistence.*;

@Entity
@Table( schema = "schema2", name="table2")
public class Table2 {
    @Id
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;


    //No default constructor for entity...
    public Table2() {
    }

    public Table2(int id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
