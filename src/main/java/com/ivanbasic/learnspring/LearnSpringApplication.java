package com.ivanbasic.learnspring;

import com.ivanbasic.learnspring.configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class LearnSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(LearnSpringApplication.class, args);
    }
}
