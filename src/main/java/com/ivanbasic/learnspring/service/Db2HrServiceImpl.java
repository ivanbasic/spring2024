package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.db2.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Db2HrServiceImpl implements Db2HrService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public String test() {
        return "Number of employees="+employeeRepo.count();
    }
}
