package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import com.example.jpastudy.base.domain.entity.Team;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProxyTest {

    static EntityManagerFactory emf;
    EntityManager entityManager;


    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpaBook");
    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
    }

    @AfterEach
    void after() {
        entityManager.close();
    }

    @Test
    void 프록시객체가_식별자를_조회하기_전까지는_쿼리가_날아가지_않는다() {
        transactional(entityManager, () -> {
            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            entityManager.persist(member1);
            flushAndClear(entityManager);

            Long nonValidPk = 2L;
            Member member = entityManager.getReference(Member.class, nonValidPk);
            assertThat(member.getId()).isEqualTo(nonValidPk);
        });
    }

    @Test
    void 프록시객체가_식별자외_컬럼을_조회하면_쿼리가_날아간다() {
        transactional(entityManager, () -> {
            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            entityManager.persist(member1);
            flushAndClear(entityManager);

            Long nonValidPk = 2L;
            Member member = entityManager.getReference(Member.class, nonValidPk);
            assertThrows(EntityNotFoundException.class,
                    member::getUsername
            );

        });
    }

    @Test
    void 프록시객체의_타입은_하이버네이트_프록시이다() {
        transactional(entityManager, () -> {
            //멤버 1 저장
            Member member1 = Member.builder()
                    .username("지영")
                    .build();
            entityManager.persist(member1);
            flushAndClear(entityManager);

            Member member = entityManager.getReference(Member.class, member1.getId());
            Hibernate.unproxy(member);
            assertThat(member.getClass().getName()).contains("Member$HibernateProxy");
        });
    }


    void flushAndClear(EntityManager entityManager) {
        entityManager.flush();
        entityManager.clear();
    }

    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }
}
