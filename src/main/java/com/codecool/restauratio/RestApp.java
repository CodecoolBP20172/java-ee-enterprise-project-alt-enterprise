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

        List<Food> list = new ArrayList<>();
        list.add(f);

        Restaurant r = new Restaurant("restaurant", "good", "here", list, 100);

        Order order = new Order("here", list, user1, r);

        Reservation reservation = new Reservation(100, r, user2);


        transaction.begin();
        em.persist(user1);
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
