package com.jy.lim.controller;

import com.jy.lim.CustomResponseEntity;
import com.jy.lim.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity responseEntityTest() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new CustomResponseEntity().getResponseEntity(TestDto.builder().success(true).build(),"안녕?" , HttpStatus.ACCEPTED);
    }

    @GetMapping("/test2")
    public String responseEntityTest2() {

        return "왜...";
    }

}
