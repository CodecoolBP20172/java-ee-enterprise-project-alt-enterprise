package com.codecool.restauratio.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseUtility {
    private static EntityManager em;

    public static EntityManager getEntityManager(String persistenceUnit) {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit);
            em = emf.createEntityManager();
        }
        return em;
    }

}
