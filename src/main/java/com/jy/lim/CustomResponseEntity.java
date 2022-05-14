package com.jy.lim;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

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
        Map<String, Object> map =Map.of("code" , 200000
                , "dd" ,2);
        this.success = 1;
        this.code = "200";
        this.message = message;
        this.data = object;
        return new ResponseEntity<>(map , HttpStatus.OK);

    }



}
