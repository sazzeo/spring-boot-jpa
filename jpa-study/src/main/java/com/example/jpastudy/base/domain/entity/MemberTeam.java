package com.example.jpastudy.base.domain.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@IdClass(MemberTeamId.class)
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
@Setter
@Getter
public class MemberTeam {

    @Id
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Id
    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    private String nickname;
}
