package com.ivanbasic.learnspring;

import com.ivanbasic.learnspring.configuration.RsaKeyProperties;
import com.ivanbasic.learnspring.controller.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class LearnSpringApplication {
    private static final Logger LOG = LoggerFactory.getLogger(LearnSpringApplication.class);

    public static void main(String[] args) {
        LOG.info("LearnSpringApplication.main() started");

        SpringApplication.run(LearnSpringApplication.class, args);
    }
}
