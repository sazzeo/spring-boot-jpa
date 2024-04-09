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
        assertThat(findedMember.getTeam().getName()).isEqualTo("팀2");
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
        assertThat(findedMember.getTeam()).isNull();
    }

    @Test
    void 양방향_연관관계를_저장한다() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //팀1 저장
        Team team1 = Team.builder()
                .name("팀1")
                .build();
        entityManager.persist(team1);

        //멤버 1 저장
        Member member1 = Member.builder()
                .username("지영")
                .build();
        member1.setTeam(team1);
        entityManager.persist(member1);

        Member member2 = Member.builder()
                .username("수영")
                .build();
        member2.setTeam(team1);
        entityManager.persist(member2);

        transaction.commit();
    }

    @Test
    void 주인이_아닌쪽은_연관관계를_설정하지_못한다() {

        transactional(entityManager, () -> {
            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            entityManager.persist(member1);

            Member member2 = Member.builder()
                    .username("수영")
                    .build();
            entityManager.persist(member2);

            //팀1 저장
            Team team1 = Team.builder()
                    .name("팀1")
                    .build();
            team1.getMembers().add(member1);
            team1.getMembers().add(member2);
            entityManager.persist(team1);
            entityManager.flush();
            entityManager.clear();

            Member member = entityManager.find(Member.class, member1.getId());
            assertThat(member.getTeam()).isNull();
        });

    }

    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }

    @Test
    void 커밋후_일차캐시_삭제_테스트() {
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
        transaction.commit();

        EntityTransaction transaction1 = entityManager.getTransaction();
        transaction1.begin();

        Member findedMember = entityManager.find(Member.class, member.getId());
        System.out.println(findedMember);
        transaction1.commit();

    }

}
