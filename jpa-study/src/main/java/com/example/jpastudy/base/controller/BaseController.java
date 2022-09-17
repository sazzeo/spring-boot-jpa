package com.example.jpastudy.base.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
