package com.codecool.restauratio;

import com.codecool.restauratio.controller.RestaurantController;
import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import org.eclipse.jetty.http.HttpStatus;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

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
        enableDebugScreen();

        exception(Exception.class, (e, req, res) -> e.printStackTrace());
        staticFileLocation("/public");
        port(8888);


        get("/", (req, res) -> {
            try {
                return new ThymeleafTemplateEngine().render(RestaurantController.renderRestaurants(req, res));
            } catch (Exception e) {
                res.status(HttpStatus.SERVICE_UNAVAILABLE_503);
                return "<html><body><h1>" + res.raw().getStatus() + "</h1><p>SERVICE UNAVAILABLE</p></body></html>";
        }});


                // TODO Write route methods for basic views.
//        get("/", (Request request, Response response) -> {
//                    try {
////                        return new ThymeleafTemplateEngine().render(.renderProducts(request, response));
//                    } catch (Exception e) {
//                        response.status(HttpStatus.SERVICE_UNAVAILABLE_503);
//                        return "<html><body><h1>" + response.raw().getStatus() + "</h1><p>SERVICE UNAVAILABLE</p></body></html>";
//                    }
//
//                }
//        );

                em.close();
                emf.close();
        }
    }
