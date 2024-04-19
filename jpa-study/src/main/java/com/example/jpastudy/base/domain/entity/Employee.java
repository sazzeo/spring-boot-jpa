package com.example.jpastudy.base.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

//값타입 테스트를 위한 객체

@Entity
@NoArgsConstructor
@Builder
@Getter
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private Period workPeriod;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "startDate", column = @Column(name = "startDate2")),
            @AttributeOverride(name = "endDate", column = @Column(name = "endDate2")
            )})
    private Period workPeriod2;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Period> periods;

}
