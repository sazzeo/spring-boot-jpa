package com.example.jpastudy.base.domain.repository;

import com.example.jpastudy.base.domain.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// ctrl + shift + t  => test 자동생성


class MemberJpaRepositoryTest {


    @Test
    @Transactional
    @Rollback(false)
    void useEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaBook");
        EntityManager entityManager = emf.createEntityManager();

        Member member = Member.builder()
                .id(1L)
                .username("지영")
                .build();

        entityManager.persist(member);
        Member member1 = entityManager.find(Member.class, 1L);


        System.out.println("member1 = " + member1);
    }


}