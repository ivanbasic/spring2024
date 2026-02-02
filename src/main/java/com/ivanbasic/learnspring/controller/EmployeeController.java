package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.model.db2.Employee;
import com.ivanbasic.learnspring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/db2/employee/count")
    public  String test() {
        LOG.info("Controller Employee.test() started");

        return employeeService.test();
    }
    
    /**
     * Lesson 056: Get employees in my department
     * - ADMIN users see all employees
     * - Employees see their department colleagues
     */
    @GetMapping("/db2/employees/my-team")
    public List<Employee> getMyTeam(Authentication auth) {
        LOG.info("Controller Employee.getMyTeam() called by user: {}", auth.getName());
        
        return employeeService.getMyTeam(auth);
    }

}