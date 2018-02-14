package com.codecool.restauratio.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantDaoTest {

    @BeforeEach
    void prepareDB() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testRestaurantPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        transaction.commit();
        em.close();
    }

    @Test
    void getAll() {
    }

    @Test
    void getById() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }
}