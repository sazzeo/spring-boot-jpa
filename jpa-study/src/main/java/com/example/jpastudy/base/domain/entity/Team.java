package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = {CascadeType.PERSIST , CascadeType.REMOVE})
    @JoinColumn(name = "teamId")
    @Builder.Default
    private List<Member> members = new ArrayList<>();

    public void addMember(Member member) {
        members.add(member);
    }

}
