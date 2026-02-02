package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.model.db2.Employee;
import com.ivanbasic.learnspring.repository.db2.EmployeeRepo;
import com.ivanbasic.learnspring.security.EmployeeLinkedUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    EmployeeRepo employeeRepo;
    
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public String test() {
        return "Number of employees="+employeeRepo.count();
    }
    
    /**
     * Lesson 055: SQL-based filtering (better than @PostFilter)
     * 
     * Business rules:
     * - ADMIN users see all employees
     * - MANAGER users see only employees in their department
     * - Users without employee_id see nothing
     */
    @Override
    public List<Employee> getEmployeesForManager(Authentication auth) {
        // Check if user is ADMIN
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("SCOPE_ADMIN"))) {
            return employeeRepo.findAll();
        }

        // Get username from auth (works for both JWT and Basic)
        String username = auth.getName();

        // Load EmployeeLinkedUserDetails
        EmployeeLinkedUserDetails user = (EmployeeLinkedUserDetails) userDetailsService.loadUserByUsername(username);

        // No employee_id? No employees
        if (user.getEmployeeId() == null) {
            return Collections.emptyList();
        }

        // Find manager and return department employees
        Employee manager = employeeRepo.findByEmployeeId(user.getEmployeeId());
        if (manager == null || manager.getDepartment() == null) {
            return Collections.emptyList();
        }

        return employeeRepo.findByDepartmentDepartmentId(
                manager.getDepartment().getDepartmentId()
        );
    }
}