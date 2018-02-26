package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
