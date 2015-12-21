package com.example.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rest")
public class Rest {

    @RequestMapping
    public String testSocket() {
        return "Controller Response";
    }

}
