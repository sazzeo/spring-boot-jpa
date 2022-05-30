package com.jy.lim.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@ToString
public class ValidDto {

    @NotNull
    private String name;

}
