package com.apeng.smartcanteenbackend.repository;

import com.apeng.smartcanteenbackend.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {
}
