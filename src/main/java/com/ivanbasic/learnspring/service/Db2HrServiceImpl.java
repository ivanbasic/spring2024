package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.db2.model.Department;
import com.ivanbasic.learnspring.db2.model.Employee;
import com.ivanbasic.learnspring.db2.repo.DepartmentRepo;
import com.ivanbasic.learnspring.db2.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@Service
public class Db2HrServiceImpl implements Db2HrService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    @Override
    public String test() {
        String toReturn = "";

        Department department1 = new Department(1, "IT");
        Department departments2 = new Department(2, "HR");

        final LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.JANUARY, 20, 6, 15, 30);
        final LocalDateTime dateTime2 = LocalDateTime.of(2016, Month.JANUARY, 20, 6, 15, 30);

        Employee employee1 = new Employee(1, "Ada", dateTime1, department1);
        Employee employee2 = new Employee(2, "Niklaus", dateTime1, department1);
        Employee employee3 = new Employee(3, "Karl", dateTime2, departments2);

        departmentRepo.save(department1);
        departmentRepo.save(departments2);

        employeeRepo.save(employee1);
        employeeRepo.save(employee2);
        employeeRepo.save(employee3);


        toReturn += "dept=" + departmentRepo.count() + ", emp=" + employeeRepo.count();

        toReturn += "\nid,1=" + employeeRepo.countByEmployeeId(1);
        toReturn += "\nid,1-3=" + employeeRepo.countByEmployeeIdBetween(1, 3);

        toReturn += "\nname,Ada=" + employeeRepo.countByFirstName("Ada");
        toReturn += "\nname,A%=" + employeeRepo.countByFirstNameStartingWith("A");
        toReturn += "\nname,%l%=" + employeeRepo.countByFirstNameContaining("l");

        toReturn += "\ndate btw=" + employeeRepo.countByHireDateBetween(dateTime1, dateTime1);

        toReturn += "\ndept,in=" + employeeRepo.countByDepartmentDepartmentIdIn(Arrays.asList(1, 2));

        toReturn += "\nid&name=" + employeeRepo.countByEmployeeIdAndFirstName(1, "Ada");

        toReturn += "\nJPQL=" + employeeRepo.customJPQLCountByEmployeeId(1);

        toReturn += "\nNative=" + employeeRepo.customNativeCountByEmployeeId(1);

        return toReturn;
    }
}
