package com.jy.lim.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
