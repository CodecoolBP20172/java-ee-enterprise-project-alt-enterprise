package com.codecool.restauratio.controller;

import com.codecool.restauratio.models.Food;
import com.codecool.restauratio.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderControllerREST {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/food/delete/", method = RequestMethod.POST)
    public ResponseEntity<List<Food>> deleteFoodFromOrder(@RequestBody Map<String, String> data) {
        return new ResponseEntity<>(orderService.deleteFoodFromOrder(data.get("orderId")), HttpStatus.OK);
    }
}
