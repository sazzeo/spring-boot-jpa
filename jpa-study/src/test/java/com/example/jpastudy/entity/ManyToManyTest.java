package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import com.example.jpastudy.base.domain.entity.MemberTeam;
import com.example.jpastudy.base.domain.entity.MemberTeamId;
import com.example.jpastudy.base.domain.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ManyToManyTest {

    static EntityManagerFactory emf;
    EntityManager entityManager;
    EntityManager entityManager2;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpaBook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        entityManager2 = emf.createEntityManager();
    }

    @AfterEach
    void after() {
        entityManager.close();
        entityManager2.close();
    }


    @Test
    void 다대다_관계의_경우_연결테이블이_필요하다() {
//        transactional(entityManager, () -> {
//            Team team1 = Team.builder()
//                    .name("팀1")
//                    .build();
//            entityManager.persist(team1);
//
//            //멤버 1 저장
//            Member member1 = Member.builder()
//                    .username("지영")
//                    .build();
//            member1.addTeam(team1);
//            entityManager.persist(member1);
//
//            flushAndClear(entityManager);
//
//            Member member = entityManager.find(Member.class, member1.getMemberId());
//            assertThat(member.getTeams().stream()
//                    .map(Team::getTeamId)
//                    .collect(Collectors.toList()))
//                    .containsExactly(team1.getTeamId());
//        });

    }

    @Test
    void 연결테이블의_다른컬럼이_존재할경우_엔티티를_별도로_정의해서_사용해야한다() {
        transactional(entityManager, () -> {
            Team team1 = Team.builder()
                    .name("팀1")
                    .build();
            entityManager.persist(team1);

            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            entityManager.persist(member1);

            MemberTeam memberTeam = MemberTeam.builder()
                    .team(team1)
                    .member(member1)
                    .nickname("팀1지영")
                    .build();

            entityManager.persist(memberTeam);
            flushAndClear(entityManager);

            MemberTeamId memberTeamId = new MemberTeamId(member1.getMemberId(), team1.getTeamId());
            MemberTeam memberTeam1 = entityManager.find(MemberTeam.class, memberTeamId);
            System.out.println(memberTeam1);

        });
    }


    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }

    void flushAndClear(EntityManager entityManager) {
        entityManager.flush();
        entityManager.clear();
    }
}
