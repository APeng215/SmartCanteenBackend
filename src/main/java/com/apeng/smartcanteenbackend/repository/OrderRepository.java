package com.apeng.smartcanteenbackend.repository;

import com.apeng.smartcanteenbackend.entity.Order;
import com.apeng.smartcanteenbackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findByUser(User user);

}
