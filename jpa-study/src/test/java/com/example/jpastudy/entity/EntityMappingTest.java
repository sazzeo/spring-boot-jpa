package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import org.junit.jupiter.api.*;

import javax.persistence.*;

public class EntityMappingTest {
    static EntityManagerFactory emf;

    EntityManager entityManager;

    EntityTransaction transaction;

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

    @DisplayName("id 값 변경 테스트 => JPA에서는 에러난다.")
    @Test
    void 준영속_clear_test() {

        Member member = Member.builder()
                .username("지영")
                .age(28)
                .build();

        entityManager.persist(member);
        member.setId(3L);

        Assertions.assertThrows(PersistenceException.class, () ->
                transaction.commit()
        );
    }

    @DisplayName("@Access getter 테스트")
    @Test
    void AccessGetterTest() {

        Member member = Member.builder()
                .username("지영")
                .age(28)
                .description("설명")
                .build();

        entityManager.persist(member);
        transaction.commit();
    }

}