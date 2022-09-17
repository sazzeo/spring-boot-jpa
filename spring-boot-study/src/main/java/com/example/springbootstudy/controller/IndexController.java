package com.example.springbootstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {


    //어노테이션 작동 테스트
    @GetMapping("/")
    public String responseEntityTest3() {

        return "index";
    }


}
