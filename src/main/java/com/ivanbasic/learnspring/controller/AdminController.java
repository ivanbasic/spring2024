package com.ivanbasic.learnspring.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String root() {
        return "Admin root";
    }

    @GetMapping("/info")
    public String info() {
        return "Admin info";
    }

    @GetMapping("/data")
    public String data() {
        return "Admin data";
    }



}