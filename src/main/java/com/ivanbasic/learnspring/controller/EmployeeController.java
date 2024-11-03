package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/db2/employee/count")
    public  String test() {
        return employeeService.test();
    }

}