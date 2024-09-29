package com.ivanbasic.learnspring.controler;

import com.ivanbasic.learnspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customer/count")
    public long count() {
        return customerService.count();
    }

}
