package com.ivanbasic.learnspring.transactional_tests;

import com.ivanbasic.learnspring.model.db1.Customer;
import com.ivanbasic.learnspring.repository.db1.CustomerRepository;
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
@ActiveProfiles("test")
@Transactional()
public class Db1TransactionalTest {
    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Order(1)
    public void transaction1Test() {

        assertEquals(0, customerRepository.count());
    }

    @Test
    @Order(2)
    public void transaction2Test() {
        Customer customer = getCustomer(1);
        customerRepository.save(customer);
        assertEquals(1, customerRepository.count());
    }

    @Test
    @Order(3)
    public void transaction3Test() {

        assertEquals(0, customerRepository.count());
    }


    private Customer getCustomer(int ii) {
        Customer customer = new Customer("" + ii, "" + ii);
        return customer;
    }

}
