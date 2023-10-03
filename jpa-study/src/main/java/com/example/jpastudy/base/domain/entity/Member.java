package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Setter
public class Member {

    @Id
    private Long id;

    private String username;


}
