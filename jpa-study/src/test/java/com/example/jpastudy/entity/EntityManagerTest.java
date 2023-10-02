package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import org.junit.jupiter.api.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

// ctrl + shift + t  => test 자동생성


class EntityManagerTest {

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

    @DisplayName("엔티티 저장해보기")
    @Test
    void useEntityManager() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .id(3L)
                .username("지영")
                .build();

        entityManager.persist(member);
        Member member1 = entityManager.find(Member.class, 1L);

        transaction.commit(); //commit 하는 순간 저장 (flush)
        System.out.println("member1 = " + member1);
    }


    @DisplayName("준영속 상태의 entity는 저장도 되지 않는다.")
    @Test
    void 준영속_test() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Member member = Member.builder()
                .id(4L)
                .username("지영")
                .build();

        entityManager.persist(member); // 아래코드로 인해 저장되지 않음.
        entityManager.detach(member); //준영속 상태로 변경

        transaction.commit();
    }


    @DisplayName("준영속 clear 이후 테스트")
    @Test
    void 준영속_clear_test() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Member member = Member.builder()
                .id(4L)
                .username("지영")
                .build();

        entityManager.persist(member); // 아래코드로 인해 저장되지 않음.
        entityManager.clear(); //준영속 clear

        Member member2 = Member.builder()
                .id(5L)
                .username("지영")
                .build();
        entityManager.persist(member2); // clear 이후 코드 이므로 저장됨

        transaction.commit();
    }

    @DisplayName("준영속 close 테스트")
    @Test
    void 준영속_close_test() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Member member = Member.builder()
                .id(6L)
                .username("지영")
                .build();

        entityManager.persist(member); // 아래코드로 인해 저장되지 않음.
        entityManager.close(); //준영속 clear
        Member member2 = Member.builder()
                .id(7L)
                .username("지영")
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            entityManager.persist(member2); // clear 이후 코드 이므로 저장됨
        });

        transaction.commit();
    }
}