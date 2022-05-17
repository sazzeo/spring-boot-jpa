package com.jy.lim.service;

import com.jy.lim.common.annotation.AnnotationPractice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {


    @Transactional
    public void test(@AnnotationPractice String a) {
        System.out.println(a);

    }
}
