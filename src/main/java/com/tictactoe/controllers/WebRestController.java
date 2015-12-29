package com.tictactoe.controllers;

import com.tictactoe.domain.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class WebRestController {

    @RequestMapping
    public HttpEntity<String> testRest() {
        HttpEntity<String> entity = new HttpEntity<>();
        entity.setResult("Controller Response");
        return entity;
    }

}
