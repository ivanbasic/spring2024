package com.ivanbasic.learnspring.transactional_tests;

import com.ivanbasic.learnspring.model.db2.Department;
import com.ivanbasic.learnspring.repository.db2.DepartmentRepo;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles( "test" )
@Transactional("DB2")
public class Db2TransactionalTest {
    @Autowired
    DepartmentRepo departmentRepo;

    @Test
    @Order(1)
    public void transaction1Test() {
     assertEquals(2,   departmentRepo.count());
    }

    @Test
    @Order(2)
    public void transaction2Test() {
        Department d3 = getDepartment(3);
        departmentRepo.save(d3);
        assertEquals(3,   departmentRepo.count());
    }

    @Test
    @Order(3)
    public void transaction3Test() {
        assertEquals(2,   departmentRepo.count());
    }


    private Department getDepartment(int ii) {
        Department department = new Department(ii, "department-"+ii);
        return department;
    }

}
