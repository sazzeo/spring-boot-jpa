package com.example.jpastudy.base.domain.repository;

import com.example.jpastudy.base.domain.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


//JpaRepository 인터페이스 등장 이전에는 JPA를 이렇게 사용했었음.
@Repository
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em;  //영속성을 관리하는 클래스
    /*
    EntityManagerFactory에서 EntityManager를 생성한다.
    EntityManager는 스레드당 1개 , Factory는 어플리케이션단에서 단 1개만 생성되어야 한다.
    내부적으로 DB connection pool을 사용해서 db에 접근하게 됨

     */

    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    public Member find(Long id) {
        return em.find(Member.class , id);
    }
}
