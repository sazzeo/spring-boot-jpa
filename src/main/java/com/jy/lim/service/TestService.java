package com.jy.lim.service;

import com.jy.lim.common.annotation.AnnotationPractice;
import com.jy.lim.dto.ValidDto;
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
