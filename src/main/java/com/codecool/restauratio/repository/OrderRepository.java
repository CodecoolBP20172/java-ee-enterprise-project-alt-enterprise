package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Order;
import com.codecool.restauratio.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o " +
            "WHERE user_id=:userId " +
            "AND target_order_restaurant_id=:restaurantId " +
            "AND isActive = true")
    Order findActiveOrder(
            @Param("userId") Integer userId,
            @Param("restaurantId") Integer restaurantId);

    List<Order> findAllByUser(User user);
}
