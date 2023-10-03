package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.assertj.core.api.Assertions.assertThat;

// ctrl + shift + t  => test 자동생성


class EntityManagerTest {

    static EntityManagerFactory emf;
    EntityManager entityManager;

    EntityTransaction transaction ;

    @BeforeAll
    static void beforeAll() {
        emf = Persistence.createEntityManagerFactory("jpaBook");

    }

    @BeforeEach
    void setUp() {
        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }


    @DisplayName("엔티티 저장해보기")
    @Test
    void useEntityManager() {
        Member member = Member.builder()
                .id(3L)
                .username("지영")
                .build();
        entityManager.persist(member);
        transaction.commit();

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

    @DisplayName("준영속 병합 test")
    @Test
    void 준영속_merge() {

        Member member = Member.builder()
                .id(6L)
                .username("지영")
                .build();

        entityManager.persist(member); // 아래코드로 인해 저장되지 않음.
        transaction.commit();
        entityManager.close(); //준영속 상태로 변경


        member.setUsername("지영ㅇㅅㅇ"); //준영속 상태에서 변경

        EntityManager entityManager2 = emf.createEntityManager();
        EntityTransaction transaction2 = entityManager2.getTransaction();
        transaction2.begin();
        //새롭게 영속 상태 획득
        Member mergeMember = entityManager2.merge(member);

        assertThat(member).isNotEqualTo(mergeMember); //준영속 상태의 member와 mergeMember는 다른 객체이다.
        transaction2.commit();
    }

}