package com.ivanbasic.learnspring.db2.repo;

import com.ivanbasic.learnspring.db2.model.Employee;
import com.ivanbasic.learnspring.dto.EmployeeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles( "test" )
public class Db2RepoTest {
    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    DepartmentRepo departmentRepo;

    final LocalDateTime dateTime1 = LocalDateTime.of(2015, Month.NOVEMBER, 21, 14, 30, 15);

    @Test
    public void countTest() {

        long c01 = departmentRepo.count();
        long c02 = employeeRepo.count();
        long c03 = employeeRepo.countByEmployeeId(1);
        long c04 = employeeRepo.countByEmployeeIdBetween(1, 3);
        long c05 = employeeRepo.countByFirstName("Ada");
        long c06 = employeeRepo.countByFirstNameStartingWith("A");
        long c07 = employeeRepo.countByFirstNameContaining("l");
        long c08 = employeeRepo.countByHireDateBetween(dateTime1, dateTime1);
        long c09 = employeeRepo.countByDepartmentDepartmentIdIn(Arrays.asList(1, 2));
        long c10 = employeeRepo.countByEmployeeIdAndFirstName(1, "Ada");
        long c11 = employeeRepo.customJPQLCountByEmployeeId(1);
        long c12 = employeeRepo.customNativeCountByEmployeeId(1);

        assertEquals(2,c01);
        assertEquals(3,c02);
        assertEquals(1,c03);
        assertEquals(3,c04);
        assertEquals(1,c05);
        assertEquals(1,c06);
        assertEquals(2,c07);
        assertEquals(1,c08);
        assertEquals(3,c09);
        assertEquals(1,c10);
        assertEquals(1,c11);
        assertEquals(1,c12);
    }

    @Test
    public void findTest() {

        Employee employee11 = employeeRepo.findByEmployeeId(1);
        Employee employee12 = employeeRepo.customJPQLFindByEmployeeId(1);
        Employee employee13 = employeeRepo.customNativeFindByEmployeeId(1);
        List<Employee> employeeList = employeeRepo.findAll();
        List<EmployeeDto> employeeDtoList = employeeRepo.getEmployeeDtos();

        assertNotNull(employee11);
        assertNotNull(employee12);
        assertNotNull(employee13);

        assertNotNull(employeeList);
        assertEquals(3, employeeList.size());

        assertNotNull(employeeDtoList);
        assertEquals(3, employeeDtoList.size());

    }


}
