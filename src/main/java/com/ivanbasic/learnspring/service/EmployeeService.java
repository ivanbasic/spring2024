package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.model.db2.Employee;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface EmployeeService {
    String test();
    
    // Lesson 056: Get employees visible to current user
    List<Employee> getMyTeam(Authentication auth);
}