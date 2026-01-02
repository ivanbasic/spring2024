package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}