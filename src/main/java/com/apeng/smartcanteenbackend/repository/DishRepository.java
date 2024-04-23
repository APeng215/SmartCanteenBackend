package com.apeng.smartcanteenbackend.repository;

import com.apeng.smartcanteenbackend.entity.Dish;
import org.springframework.data.repository.CrudRepository;

public interface DishRepository extends CrudRepository<Dish, Long> {
}
