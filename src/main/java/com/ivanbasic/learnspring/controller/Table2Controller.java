package com.ivanbasic.learnspring.controller;


import com.ivanbasic.learnspring.service.Table2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Table2Controller {

    @Autowired
    Table2Service table2Service;

    @GetMapping("/db2/table2/count")
    public long test() {
        return table2Service.test();
    }

}