package com.codecool.restauratio;

import com.codecool.restauratio.models.users.Admin;
import com.codecool.restauratio.utils.DatabaseUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RestApp {

    public static void populateDb(EntityManager em) {

        EntityTransaction transaction = em.getTransaction();

        Admin admin1 = new Admin("Xattus", "egy123");
        Admin admin2 = new Admin("Soma", "egy1234");

        transaction.begin();
        em.persist(admin1);
        em.persist(admin2);
        transaction.commit();
    }

    public static void main(String[] args) {
        EntityManager em = DatabaseUtility.getEntityManager();
        populateDb(em);

    }
}
