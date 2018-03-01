package com.codecool.restauratio.services;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.Restaurant;
import com.codecool.restauratio.models.users.User;
import com.codecool.restauratio.repository.FoodRepository;
import com.codecool.restauratio.repository.OrderRepository;
import com.codecool.restauratio.repository.RestaurantRepository;
import com.codecool.restauratio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    //TODO Usage Driven Development

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void findActiveOrderForUser (Date currentDate, Integer userId, Integer foodId, Integer restaurantId) {
        Order orderToSave = orderRepository.findActiveOrder(userId, restaurantId);
        User orderUser = userRepository.findOne(userId);
        Food foodToSave = foodRepository.findOne(foodId);
        Restaurant restaurantFromOrder = restaurantRepository.findOne(restaurantId);
        if (orderToSave != null) {
            orderToSave.getFoodList().add(foodToSave);
        } else {
            List<Food> foodListToSave = new ArrayList<>();
            foodListToSave.add(foodToSave);
            orderToSave = new Order(currentDate, foodListToSave, orderUser, restaurantFromOrder);
        }
        saveOrder(orderToSave);
    }

    public void decreaseOrderQuantity (Integer orderId, Integer foodId) {
        Order orderToSave = orderRepository.findOne(orderId);
        Food foodToRemove = foodRepository.findOne(foodId);
        List <Food> currentFoodList = orderToSave.getFoodList();
        for (int i =0; i <currentFoodList.size(); i++) {
            if (foodToRemove == currentFoodList.get(i)) {
                currentFoodList.remove(i);
            }
        }
        orderRepository.save(orderToSave);
    }

    private void saveOrder (Order orderToSave) {
        orderRepository.save(orderToSave);
    }



}
