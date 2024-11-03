package com.ivanbasic.learnspring.repository.db2;

import com.ivanbasic.learnspring.model.db2.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
