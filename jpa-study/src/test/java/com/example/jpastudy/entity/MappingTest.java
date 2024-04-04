package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Team;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MappingTest {

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
    void member에_team을_매핑한다() {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Team team = Team.builder()
                .name("팀1")
                .build();

        entityManager.persist(team);

        transaction.commit();

    }

}
