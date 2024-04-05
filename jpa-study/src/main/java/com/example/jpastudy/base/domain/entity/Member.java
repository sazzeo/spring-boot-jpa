package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Setter
@Getter
//@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    private String description;

    @OneToOne
    @JoinColumn(name = "teamId") //fk 키가 member에게 존재함
    private Team team;

    @Lob
    @Access(AccessType.PROPERTY)
    public String getDescription() {
        return description + "getter";
    }

}
