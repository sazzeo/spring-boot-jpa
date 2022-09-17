package com.example.springbootstudy.service;

import com.example.springbootstudy.common.annotation.AnnotationPractice;
import com.example.springbootstudy.dto.ValidDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
@Validated
@Service
public class TestService {


    @Transactional
    public void test(@AnnotationPractice String a) {
        System.out.println(a);

    }

    @Transactional
    public void dtoTest(@Valid ValidDto validDto) {
        System.out.println(validDto);
    }


}
