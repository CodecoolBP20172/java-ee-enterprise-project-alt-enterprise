package com.codecool.restauratio;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Request;
import com.codecool.restauratio.models.users.Admin;
import com.codecool.restauratio.models.users.Guest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RestApp {

    public static void populateDb(EntityManager em) {

        EntityTransaction transaction = em.getTransaction();

        Admin admin1 = new Admin("Xattus", "egy123");
        Guest guest1 = new Guest("Soma", "egy1234");
        Request order1 = new Order();
        guest1.getOrders().add(order1);

        transaction.begin();
        em.persist(admin1);
        em.persist(guest1);
        em.persist(order1);
        transaction.commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantSetterPU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        em.close();
        emf.close();
    }


}
