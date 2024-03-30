package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import javax.persistence.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntityManagerTest {

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
    void 엔티티를_저장한다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);
        Member member1 = entityManager.find(Member.class, 1L);
        Member member2 = entityManager.find(Member.class, 1L);

        transaction.commit(); //commit 하는 순간 저장 (flush)
    }

    @Test
    void 동일한_식별자_값을_갖는_엔티티는_동일성이_보장된다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);

        Long id = member.getId();

        //조회 쿼리 날아가지 않음
        Member member1 = entityManager.find(Member.class, 1L);
        Member member2 = entityManager.find(Member.class, id);
        transaction.commit(); //commit 하는 순간 저장 (flush) 
        //flush 한 후의 객체도 동일성 보장
        Member member3 = entityManager.find(Member.class, id);
        assertAll(
                () -> assertThat(member1 == member2).isTrue(),
                () -> assertThat(member1 == member3).isTrue());
    }

    @Test
    void 다른_영속성_컨텍스트를_사용하는_경우_동일성을_보장하지_않는다() {
        EntityTransaction transaction = entityManager.getTransaction();
        EntityTransaction transaction2 = entityManager2.getTransaction();
        transaction2.begin();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);

        Long id = member.getId();

        Member member1 = entityManager.find(Member.class, 1L);
        Member member2 = entityManager.find(Member.class, id);
        transaction.commit(); //commit 하는 순간 저장 (flush)
        assertThat(member1 == member2).isTrue();

        //다른 entityManager 사용할 때 select문 날림
        Member member3 = entityManager2.find(Member.class, id);
        assertThat(member1 != member3).isTrue();
    }

    @Test
    void 컬럼을_일부만_수정해도_모든_컬럼에_대한_쿼리가_만들어진다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);

        member.setAge(20);
        transaction.commit();

        assertThat(member.getAge()).isEqualTo(20);
    }

    @Test
    void 플러시_모드가_COMMIT_일떄는_JPQL을_날려도_플러시_되지_않는다() {
        EntityTransaction transaction = entityManager.getTransaction();
        entityManager.setFlushMode(FlushModeType.COMMIT);

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);

        assertThrows(NoResultException.class,
                () -> entityManager.createQuery("select m from Member m where m.id =: id", Member.class)
                        .setParameter("id", member.getId())
                        .getSingleResult()
        );

    }

    @Test
    void 준영속_상태는_쿼리가_날아가지_않지만_식별자는_가지고_있다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);
        entityManager.detach(member);

        Assertions.assertThat(member.getId()).isNotNull();
        transaction.commit();
    }

    @Test
    void 엔티티매니저를_close_하면_더이상_기능을_쓸_수_없다() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        em.close();
        Member member = Member.builder()
                .username("지영")
                .build();

        assertThrows(IllegalStateException.class,
                () -> em.persist(member)
        );
    }

    @Test
    void 비영속_상태에서_merge하면_영속상태가_된다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.merge(member);

        transaction.commit();
    }

    @Test
    void 준영속_상태에서_merge하면_영속상태가_된다() {
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        Member member = Member.builder()
                .username("지영")
                .build();

        entityManager.persist(member);
        transaction.commit();
        entityManager.clear();

        //쿼리 다시 날림
        entityManager2.merge(member);

    }

}
