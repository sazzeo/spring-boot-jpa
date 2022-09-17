package com.example.springbootstudy.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostApiController {

    @GetMapping("/post")
    public void findPost(@PageableDefault(size=20 , sort="" , direction = Sort.Direction.DESC) Pageable pageable) {

        //finaAll 변수로 pageAll지정함.

    }

}
