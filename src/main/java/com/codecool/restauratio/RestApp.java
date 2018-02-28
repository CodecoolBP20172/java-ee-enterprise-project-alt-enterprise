package com.codecool.restauratio;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.users.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class RestApp {

    public static void populateDb(EntityManager em) {

        EntityTransaction transaction = em.getTransaction();

        User user1 = new User("józsi", "hurka", "Józsi utca 21.",true, false);
        User user2 = new User("bodri", "mecska", "Bodri utca 13.",true, true);

        Date date = new Date();

        Food f = new Food("Melák Menü", 1500, "szenya, rántotthus, rántottsajt", "király");
        Food f2 = new Food("buja burger", 200, "burger", "jó");
        Food f3 = new Food("Tele Tál Falafel", 4000, "minden ami blefér", "ragya");

        List<Food> list = new ArrayList<>();
        list.add(f);
        List<Food> list2 = new ArrayList<>();
        list2.add(f);
        list2.add(f2);
        List<Food> list3 = new ArrayList<>();
        list3.add(f);
        list3.add(f2);
        list3.add(f3);

        Restaurant r = new Restaurant("Halászcsárda", "good", "here", list, 100, user1, "/img/halasz_image.jpg");
        Restaurant r2 = new Restaurant("Csirkés", "pretty", "Mány", list2, 50, user2, "/img/csirkes_image.jpeg");
        Restaurant r3 = new Restaurant("Titiz", "bad", "Mány", list3, 10, user2, "/img/titiz_image.jpg");

        Order o1 = new Order(date, list, user1, r);
        Order o2 = new Order(date, list3, user2, r2);

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
        SpringApplication.run(RestApp.class, args);

//        post("/api/get_restaurant_by_location", RestaurantController::restaurantBrowseByLocation);

        // RESTAURANT ROUTE

//        get( "/restaurants/:restId", (request, response) -> new ThymeleafTemplateEngine().render( RestaurantController.renderRestaurant(request, response, request.params( ":restId" )) ));

    }
}
