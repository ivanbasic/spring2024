package com.ivanbasic.learnspring.controller;

import com.ivanbasic.learnspring.service.Db2HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Db2HrController {

    @Autowired
    Db2HrService db2HrService;

    @GetMapping("/db2/hr")
    public  String test() {
        return db2HrService.test();
    }

}