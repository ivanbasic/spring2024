package com.ivanbasic.learnspring.db2.repo;

import com.ivanbasic.learnspring.db2.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
