package com.ivanbasic.learnspring.controller;


import com.ivanbasic.learnspring.service.Db2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Db2Controller {

    @Autowired
    Db2Service db2Service;

    @GetMapping("/db2/test")
    public long test() {
        return db2Service.test();
    }

}