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

import static org.assertj.core.api.Assertions.assertThat;

public class OneToOneTest {

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
    void 일대일_연관관계를_설정한다() {

        transactional(entityManager, () -> {


            //팀1 저장
            Team team1 = Team.builder()
                    .name("팀1")
                    .build();
            entityManager.persist(team1);

            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .team(team1)
                    .build();
            entityManager.persist(member1);
        });

    }

    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }

}
