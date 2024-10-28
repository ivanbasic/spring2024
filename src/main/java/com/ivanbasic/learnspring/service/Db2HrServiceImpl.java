package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.db2.model.Departments;
import com.ivanbasic.learnspring.db2.model.Employees;
import com.ivanbasic.learnspring.db2.repo.DepartmentsRepo;
import com.ivanbasic.learnspring.db2.repo.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@Service
public class Db2HrServiceImpl implements Db2HrService {

    @Autowired
    EmployeesRepo employeesRepo;

    @Autowired
    DepartmentsRepo departmentsRepo;

    @Override
    public String test() {
        String toReturn = "";

        Departments departments1 = new Departments(1, "IT");
        Departments departments2 = new Departments(2, "HR");

        final LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        final LocalDateTime dateTime2 = LocalDateTime.of(2016, Month.JANUARY, 20, 6, 15, 30);

        Employees employees1 = new Employees(1, "Ada", dateTime1, departments1);
        Employees employees2 = new Employees(2, "Niklaus", dateTime1, departments1);
        Employees employees3 = new Employees(3, "Karl", dateTime2, departments2);

        departmentsRepo.save(departments1);
        departmentsRepo.save(departments2);

        employeesRepo.save(employees1);
        employeesRepo.save(employees2);
        employeesRepo.save(employees3);


        toReturn += "dept=" + departmentsRepo.count() + ", emp=" + employeesRepo.count();

        toReturn += "\nid,1=" + employeesRepo.countByEmployeeId(1);
        toReturn += "\nid,1-3=" + employeesRepo.countByEmployeeIdBetween(1, 3);

        toReturn += "\nname,Ada=" + employeesRepo.countByFirstName("Ada");
        toReturn += "\nname,A%=" + employeesRepo.countByFirstNameStartingWith("A");
        toReturn += "\nname,%l%=" + employeesRepo.countByFirstNameContaining("l");

        toReturn += "\ndate btw=" + employeesRepo.countByHireDateBetween(dateTime1, dateTime1);

        toReturn += "\ndept,in=" + employeesRepo.countByDepartmentsDepartmentIdIn(Arrays.asList(1, 2));

        toReturn += "\nid&name=" + employeesRepo.countByEmployeeIdAndFirstName(1, "Ada");

        toReturn += "\nJPQL=" + employeesRepo.customJPQLCountByEmployeeId(1);

        toReturn += "\nNative=" + employeesRepo.customNativeCountByEmployeeId(1);

        return toReturn;
    }
}
