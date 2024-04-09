package com.example.jpastudy.inheritance;

import com.example.jpastudy.inheritance.join.Album;
import com.example.jpastudy.inheritance.join.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JoinStrategyTest {

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
    void 조인_전략_insert_test() {
        transactional(entityManager, () -> {
            Album album = Album.builder()
                    .name("아이유 앨범")
                    .price(10000)
                    .artist("아이유")
                    .build();
            entityManager.persist(album);
            entityManager.flush();
            entityManager.clear();
            entityManager.find(Album.class, album.getItemId());
        });
    }


    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }

}
