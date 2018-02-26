package com.codecool.restauratio.repository;

import com.codecool.restauratio.models.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer>{

}
