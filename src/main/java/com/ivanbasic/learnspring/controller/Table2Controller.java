package com.ivanbasic.learnspring.controller;


import com.ivanbasic.learnspring.service.Table2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Table2Controller {
    private static final Logger LOG = LoggerFactory.getLogger(Table2Controller.class);

    @Autowired
    Table2Service table2Service;

    @GetMapping("/db2/table2/count")
    public long test() {
        LOG.info("Controller Table2Controller.test() started");

        return table2Service.test();
    }

}