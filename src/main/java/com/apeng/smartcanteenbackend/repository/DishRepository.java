package com.apeng.smartcanteenbackend.repository;

import com.apeng.smartcanteenbackend.entity.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DishRepository extends CrudRepository<Dish, Long> {
}
