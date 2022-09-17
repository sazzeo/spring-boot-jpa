package com.example.springbootstudy.common.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
@Getter
public class CustomResponseEntity {

    private int success;
    private String code;
    private String message;
    private Object data;

    private Object error;

    public ResponseEntity<CustomResponseEntity> getResponseEntity(Object object, String message, HttpStatus status) {
        this.success = 1;
        this.code = "200";
        this.message = message;
        this.data = object;
        return new ResponseEntity<>(this , status);

    }

    public ResponseEntity<Map> ok(Object object, String message) {
        Map<String, Object> map = new HashMap<>();
                map.put("code" , 200000);
                map.put("dd" ,2);
        this.success = 1;
        this.code = "200";
        this.message = message;
        this.data = object;
        return new ResponseEntity<>(map , HttpStatus.OK);

    }



}
