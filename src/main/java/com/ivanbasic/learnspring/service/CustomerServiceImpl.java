package com.ivanbasic.learnspring.service;

import com.ivanbasic.learnspring.repository.db1.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService  {

    @Autowired
    CustomerRepository customerRepository;


    @Override
    public long count() {
        return customerRepository.count();
    }

}
