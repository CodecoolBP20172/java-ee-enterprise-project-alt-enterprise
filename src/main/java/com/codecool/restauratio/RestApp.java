package com.codecool.restauratio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RestApp {

    public static void populateDb(EntityManager em) {

        EntityTransaction transaction = em.getTransaction();

        //instantiate objects

        transaction.begin();
        //persist objects em.persist(object)
        transaction.commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaexamplePU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);
    }
}
