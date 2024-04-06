package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Long memberId;

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


//    @ManyToMany
//    @Builder.Default
//    private List<Team> teams = new ArrayList<>();

//    public void addTeam(Team team) {
//        this.teams.add(team);
//    }

}
