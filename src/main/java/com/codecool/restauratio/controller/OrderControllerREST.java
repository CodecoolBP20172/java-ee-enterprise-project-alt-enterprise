package com.codecool.restauratio.controller;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.repository.OrderRepository;
import com.codecool.restauratio.services.OrderService;
import com.codecool.restauratio.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Date;
import java.util.Map;

@RestController
public class OrderControllerREST {

    @Autowired
    private RestaurantService restService;
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepo;

    @RequestMapping(value = "/api/remove_food_from_order", method = RequestMethod.POST)
    public ResponseEntity<List<Food>> deleteFoodFromOrder(@RequestBody Map<String, String> data) {
        orderService.decreaseOrderQuantity(
                Integer.parseInt(data.get("orderId")),
                Integer.parseInt(data.get("foodId")));
        return new ResponseEntity<>(orderRepo.getOne(Integer.parseInt(data.get("orderId"))).getFoodList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/order_food", method = RequestMethod.POST)
    public ResponseEntity<String> getOrderDataForInteraction(@RequestBody Map<String, String> data) {
        Date currentDate = new Date();
        orderService.findActiveOrderForUser(
                currentDate,
                Integer.parseInt(data.get("userId")),
                Integer.parseInt(data.get("foodId")),
                Integer.parseInt(data.get("restaurantId"))
        );
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
