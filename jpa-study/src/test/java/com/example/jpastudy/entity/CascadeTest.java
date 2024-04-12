package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import com.example.jpastudy.base.domain.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CascadeTest {
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
    void cascade옵션으로_자식객체도_한번에_영속화_시킬수_있다() {

        transactional(entityManager, () -> {
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            Member member2 = Member.builder()
                    .username("수영")
                    .build();
            //member 영속화 생략

            //team 저장
            Team team = Team.builder()
                    .name("팀1")
                    .build();
            team.addMember(member1);
            team.addMember(member2);
            entityManager.persist(team);
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
