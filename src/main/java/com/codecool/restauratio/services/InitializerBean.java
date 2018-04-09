package com.codecool.restauratio.services;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Reservation;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.FoodRepository;
import com.codecool.restauratio.repository.OrderRepository;
import com.codecool.restauratio.repository.ReservationRepository;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InitializerBean {

    public InitializerBean(UserService userService, RestaurantService restaurantService,
                           ReservationRepository reserveRepo, OrderRepository orderRepo,
                           FoodRepository foodRepo) throws ParseException {


        User user1 = new User("józsi", "József", "Bende", "passwd", "józsi@citromail.com", "codecool", true, false);
        User user2 = new User("Erni", "Ernő", "Nemecsek", "1212", "erni@gmail.com", "home", true, true);


        Food f = new Food("Melák Menü", 1500, "szenya, rántott hús, rántott sajt", "király");
        Food f2 = new Food("burger", 200, "burger", "Elmegy");
        Food f3 = new Food("Tele Tál Falafel", 4000, "minden ami belefér", "so-so");
        Food f4 = new Food("BigKek", 2999, "Burger, meet, tomato, special sauce", "top1");
        Food f5 = new Food("Halászlé", 3888, "hal, lé", "Finom volt");
        foodRepo.save(f);
        foodRepo.save(f2);
        foodRepo.save(f3);
        foodRepo.save(f4);
        foodRepo.save(f5);

        List<Food> list = new ArrayList<>();
        list.add(f5);
        List<Food> list2 = new ArrayList<>();
        list2.add(f4);
        List<Food> list3 = new ArrayList<>();
        list3.add(f);
        list3.add(f2);
        list3.add(f3);

        Restaurant r = new Restaurant("Halászcsárda", "Regular", "Gyula", list, 100, user1, "/img/halasz_image.jpg");
        Restaurant r2 = new Restaurant("Csirkés", "Fast Food", "Mány", list3, 50, user2, "/img/csirkes_image2.jpeg");
        Restaurant r3 = new Restaurant("Titiz", "Fast Food", "Mány", list2, 10, user2, "/img/titiz_image2.jpg");
        Restaurant r4 = new Restaurant("Mal Donald", "Fast Food", "Sülysáp", list2, 10, user2, "/img/mcdonalds.jpg");

        userService.registerUser(user1);
        userService.registerUser(user2);
        restaurantService.addRestaurant(r);
        restaurantService.addRestaurant(r2);
        restaurantService.addRestaurant(r3);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = sdf.parse("2018-03-01 11:45");
        Reservation reservation = new Reservation(date, 4, "it's good fam", r2, user1);
        reserveRepo.save(reservation);

        Date orderDate = sdf.parse("2018-03-02 20:00");
        Order  order = new Order(orderDate, "codecool", list3, user1, r3);
        orderRepo.save(order);
        restaurantService.addRestaurant(r4);
    }
}
