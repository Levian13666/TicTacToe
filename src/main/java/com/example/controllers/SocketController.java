package com.example.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {

    @MessageMapping("/hello")
    @SendTo("/socket/answer")
    public String testSocket() throws Exception {
        Thread.sleep(3000); // simulated delay
        return "Socket Works!";
    }

}
