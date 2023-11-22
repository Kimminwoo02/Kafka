package com.example.orderservice.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
@Transactional
class TestEntityTest {
    @Autowired
    EntityManager em;
    @Test
    void test(){
        TestEntity entity1 = new TestEntity("test1");
        TestEntity entity2 = new TestEntity("test2");
        TestEntity entity3 = new TestEntity("test3");
        em.persist(entity1);
        em.persist(entity2);
        em.persist(entity3);

    }
}