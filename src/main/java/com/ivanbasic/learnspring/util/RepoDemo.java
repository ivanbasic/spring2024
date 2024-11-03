package com.ivanbasic.learnspring.util;

import com.ivanbasic.learnspring.LearnSpringApplication;
import com.ivanbasic.learnspring.model.db1.Customer;
import com.ivanbasic.learnspring.repository.db1.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepoDemo {
    private static final Logger log = LoggerFactory.getLogger(LearnSpringApplication.class);



    // I will skip this bean for a while.
    // I want simpler situation when postgres database is used for run and h2 database for tests.
    //@Bean
    public CommandLineRunner customerRepositoryDemo(CustomerRepository repository) {
        return (args) -> {
            log.info("");
            log.info("Customer repository demo with h2/postgres/...");

            // save a few customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));


            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });
            log.info("");


            // fetch an individual customer by ID
            Customer customer = repository.findById(1L);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");


            // fetch customers by last name
            log.info("Customer found with findByLastName('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Bauer").forEach(bauer -> {
                log.info(bauer.toString());
            });
            log.info("");

        };
    }
}
