package com.jy.lim.controller;

import com.jy.lim.CustomResponseEntity;
import com.jy.lim.dto.ListDto;
import com.jy.lim.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity responseEntityTest() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new CustomResponseEntity().getResponseEntity(TestDto.builder().success(true).build(),"안녕?" , HttpStatus.ACCEPTED);
    }

    @GetMapping("/test/test1")
    public void test1(Model model) {
        List<String> listTest = List.of("a", "b", "c","d");
        model.addAttribute("list" , listTest);

    }

    @PostMapping("/test/dto")
    public void testDto(@RequestBody @Validated ListDto listDto) {

        System.out.println(listDto);
    }



}
