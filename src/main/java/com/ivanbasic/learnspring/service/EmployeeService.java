package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.model.db2.Employee;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EmployeeService {
    String test();
    
    // Lesson 055: Get employees visible to current manager
    List<Employee> getEmployeesForManager(Authentication auth);
}
