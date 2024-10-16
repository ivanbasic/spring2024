package com.ivanbasic.learnspring;

import com.ivanbasic.learnspring.model.Customer;
import com.ivanbasic.learnspring.repo.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LearnSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }
}
