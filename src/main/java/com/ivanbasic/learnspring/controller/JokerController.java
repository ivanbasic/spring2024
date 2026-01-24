package com.ivanbasic.learnspring.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/joker")
public class JokerController {

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_JOKER')")
    public String joker() {
        return "Joker stuff";
    }


}
