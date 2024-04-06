package com.example.jpastudy.base.domain.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
public class MemberTeamId implements Serializable {
    private Long member;
    private Long team;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberTeamId)) return false;

        final MemberTeamId that = (MemberTeamId) o;

        if (!Objects.equals(member, that.member)) return false;
        return Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        int result = member != null ? member.hashCode() : 0;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }
}
