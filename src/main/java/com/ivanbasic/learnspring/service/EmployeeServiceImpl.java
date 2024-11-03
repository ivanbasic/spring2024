package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.repository.db2.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public String test() {
        return "Number of employees="+employeeRepo.count();
    }
}
