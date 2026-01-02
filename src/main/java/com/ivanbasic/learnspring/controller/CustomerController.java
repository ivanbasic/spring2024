package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping("/db1/customer/count")
    public long count() {
        LOG.info("Controller Customer.count() started");

        return customerService.count();
    }

}
