package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import com.example.jpastudy.base.domain.entity.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.*;

public class MappingTest {

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
    void member에_team을_매핑한다() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //given
        Team team = Team.builder()
                .name("팀1")
                .build();
        Member member = Member.builder()
                .username("지영")
                .build();

        //when
        entityManager.persist(team);
        member.setTeam(team);
        entityManager.persist(member);
        transaction.commit();
        Member findedMember = entityManager2.find(Member.class, member.getId());

        //then
        assertThat(findedMember.getTeam().getName()).isEqualTo("팀1");
    }

    @Test
    void member의_team을_수정한다() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //given
        Team team1 = Team.builder()
                .name("팀1")
                .build();
        Team team2 = Team.builder()
                .name("팀2")
                .build();
        Member member = Member.builder()
                .username("지영")
                .build();

        //when
        entityManager.persist(team1);
        entityManager.persist(team2);
        member.setTeam(team1);
        entityManager.persist(member);

        member.setTeam(team2);
        transaction.commit();

        Member findedMember = entityManager2.find(Member.class, member.getId());
        Assertions.assertThat(findedMember.getTeam().getName()).isEqualTo("팀2");
    }

    @Test
    void member의_team을_삭제한다() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //given
        Team team1 = Team.builder()
                .name("팀1")
                .build();
        Member member = Member.builder()
                .username("지영")
                .build();

        //when
        entityManager.persist(team1);
        member.setTeam(team1);
        entityManager.persist(member);
        member.setTeam(null);
        transaction.commit();

        Member findedMember = entityManager2.find(Member.class, member.getId());
        Assertions.assertThat(findedMember.getTeam()).isNull();
    }

}
