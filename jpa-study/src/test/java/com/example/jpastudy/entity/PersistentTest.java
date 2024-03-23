package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

class PersistentTest {

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

    @DisplayName("엔티티 동일성 비교")
    @Test
    void useEntityManager() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .id(1L)
                .username("지영")
                .build();

        entityManager.persist(member);
        Member member1 = entityManager.find(Member.class, 1L);
        Member member2 = entityManager.find(Member.class, 1L);

        Assertions.assertThat(member1 == member2).isTrue();
    }



}
