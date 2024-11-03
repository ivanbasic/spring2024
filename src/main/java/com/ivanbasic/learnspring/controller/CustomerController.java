package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/db1/customer/count")
    public long count() {
        return customerService.count();
    }

}
