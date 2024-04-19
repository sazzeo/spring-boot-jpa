package com.example.jpastudy.entity;

import com.example.jpastudy.base.domain.entity.Employee;
import com.example.jpastudy.base.domain.entity.Period;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmbeddedEntityTest {
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
    }

    @AfterEach
    void after() {
        entityManager.close();
    }

    @Test
    void 컬렉션_임베디드_타입_수정시_전부삭제후_다시_insert가_일어난다() {

        transactional(entityManager , ()-> {
            List<Period> periods = new ArrayList<>();
            periods.add(new Period(new Date() , new Date()));
            periods.add(new Period(new Date() , new Date()));
            periods.add(new Period(new Date() , new Date()));

            Employee employee = Employee.builder()
                    .periods(periods)
                    .build();

            entityManager.persist(employee);

            entityManager.flush();
            entityManager.clear();

            Employee findedEmployee = entityManager.find(Employee.class, employee.getId());
            findedEmployee.getPeriods().remove(0);

        });
    }

    void transactional(EntityManager entityManager, Runnable runnable) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        runnable.run();

        transaction.commit();
    }

}
