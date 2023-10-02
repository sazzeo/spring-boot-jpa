package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class Member {

    @Id
    private Long id;

    private String username;


}
