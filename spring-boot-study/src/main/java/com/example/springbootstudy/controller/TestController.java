package com.example.springbootstudy.controller;

import com.example.springbootstudy.common.util.CustomResponseEntity;
import com.example.springbootstudy.dto.ListDto;
import com.example.springbootstudy.dto.TestDto;
import com.example.springbootstudy.dto.ValidDto;
import com.example.springbootstudy.service.TestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;
    private final ObjectMapper mapper;


    @GetMapping("/test")
    public ResponseEntity responseEntityTest() {

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        return new CustomResponseEntity().getResponseEntity(TestDto.builder().success(true).build(),"안녕?" , HttpStatus.ACCEPTED);
    }


    @PostMapping("/test/dto")
    public void testDto(@RequestBody @Validated ListDto listDto) {

        System.out.println(listDto);
    }


    @PostMapping("/test/valid")
    public void testValid(@RequestBody Map<String, String> map) {
        ValidDto validDto = mapper.convertValue(map , ValidDto.class);
    }
}
