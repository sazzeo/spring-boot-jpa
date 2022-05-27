package com.jy.lim.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class ListDto {

    private StudentDto student;
    private SubjectDto subject;

}
