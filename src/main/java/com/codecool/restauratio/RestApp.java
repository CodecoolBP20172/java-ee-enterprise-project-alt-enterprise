package com.codecool.restauratio;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RestApp {

    public static void populateDb(EntityManager em) {

        EntityTransaction transaction = em.getTransaction();

        User user1 = new User("józsi", "hurka", true, false);
        User user2 = new User("bodri", "mecska", true, true);

        Date date = new Date();

        Food f = new Food(1500, "Melák Menü", "szenya, rántotthus, rántottsajt", "király");
        Food f2 = new Food(200, "buja burger", "burger", "jó");
        Food f3 = new Food(4000, "Tele Tál Falafel", "minden ami blefér", "ragya");

        List<Food> list = new ArrayList<>();
        list.add(f);
        List<Food> list2 = new ArrayList<>();
        list2.add(f);
        list2.add(f2);
        List<Food> list3 = new ArrayList<>();
        list3.add(f);
        list3.add(f2);
        list3.add(f3);

        Restaurant r = new Restaurant("restaurant", "good", "here", list, 100);
        Restaurant r2 = new Restaurant("r2", "pretty", "there", list2, 50);
        Restaurant r3 = new Restaurant("r3", "bad", "where", list3, 10);

        Order o1 = new Order(date, "here", list, user1, r);
        Order o2 = new Order(date, "there", list3, user2, r2);

        Reservation reservation = new Reservation(date, 100, r, user2);

        transaction.begin();
        em.persist(user1);
        em.persist(user2);
        em.persist(f);
        em.persist(f2);
        em.persist(f3);
        em.persist(r);
        em.persist(r2);
        em.persist(r3);
        em.persist(o1);
        em.persist(o2);
        em.persist(reservation);
        transaction.commit();
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("restaurantPU");
        EntityManager em = emf.createEntityManager();

        populateDb(em);

        em.close();
        emf.close();
    }
}
