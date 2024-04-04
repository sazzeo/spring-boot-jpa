package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;

@Table(name = "team")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long teamId;

    private String name;

}
