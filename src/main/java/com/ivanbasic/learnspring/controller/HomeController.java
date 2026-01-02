package com.ivanbasic.learnspring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {
    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public String home(Principal principal) {
        LOG.info("Controller Home.home() started");

        return "Hello, " + principal.getName();
    }

}