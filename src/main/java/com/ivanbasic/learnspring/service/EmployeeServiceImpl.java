package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.model.db2.Employee;
import com.ivanbasic.learnspring.repository.db2.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public String test() {
        return "Number of employees="+employeeRepo.count();
    }
    
    /**
     * Lesson 056: Username-based filtering (simpler than employee_id approach)
     * 
     * Business rules:
     * - ADMIN users see all employees
     * - Employees see only colleagues in their department
     * - Users not linked to employees see nothing
     */
    @Override
    public List<Employee> getMyTeam(Authentication auth) {
        // ADMIN sees all
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_ADMIN"))) {
            return employeeRepo.findAll();
        }
        
        // Find employee by username (direct lookup!)
        String username = auth.getName();
        Employee employee = employeeRepo.findByUsername(username);
        
        // Not an employee or no department? See nothing
        if (employee == null || employee.getDepartment() == null) {
            return Collections.emptyList();
        }
        
        // Return all employees in same department
        return employeeRepo.findByDepartmentDepartmentId(
            employee.getDepartment().getDepartmentId()
        );
    }
}