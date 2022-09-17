package com.example.springbootstudy.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TestDto {

    private String message;
    private boolean success;

    @Builder
    public TestDto(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
