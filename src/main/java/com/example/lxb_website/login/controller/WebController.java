package com.example.lxb_website.login.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    private static final Logger logger = LogManager.getLogger(WebController.class);

    @RequestMapping("hello")
    public String hello() {
        logger.info("欢迎");
        return "Hello";
    }
}
