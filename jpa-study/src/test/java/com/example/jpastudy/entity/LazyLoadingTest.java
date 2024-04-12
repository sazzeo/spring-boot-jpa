package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import com.example.jpastudy.base.domain.entity.Team;
import org.assertj.core.api.Assertions;
import org.hibernate.collection.internal.PersistentBag;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class LazyLoadingTest {

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
    void team에_member의_클래스는_PersisentBag이다() {

        transactional(entityManager, () -> {
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            Member member2 = Member.builder()
                    .username("수영")
                    .build();
            entityManager.persist(member1);
            entityManager.persist(member2);

            //team 저장
            Team team = Team.builder()
                    .name("팀1")
                    .build();
            team.addMember(member1);
            team.addMember(member2);
            entityManager.persist(team);
            flushAndClear(entityManager);

            Team findedTeam = entityManager.find(Team.class, team.getTeamId());
            Assertions.assertThat(findedTeam.getMembers()).isInstanceOf(PersistentBag.class);
        });

    }

    void flushAndClear(final EntityManager entityManager) {
        entityManager.flush();
        entityManager.clear();
    }

    void transactional(final EntityManager entityManager, final Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }
}
