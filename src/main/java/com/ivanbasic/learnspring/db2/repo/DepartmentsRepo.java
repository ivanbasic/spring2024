package com.ivanbasic.learnspring.db2.repo;

import com.ivanbasic.learnspring.db2.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepo extends JpaRepository<Departments, Integer> {
}
