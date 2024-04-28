package com.apeng.smartcanteenbackend.controller;

import com.alibaba.fastjson2.JSON;
import com.apeng.smartcanteenbackend.config.WebSecurityConfig;
import com.apeng.smartcanteenbackend.controller.util.OrderAddingRequest;
import com.apeng.smartcanteenbackend.entity.Dish;
import com.apeng.smartcanteenbackend.entity.Order;
import com.apeng.smartcanteenbackend.entity.User;
import com.apeng.smartcanteenbackend.repository.DishRepository;
import com.apeng.smartcanteenbackend.repository.OrderRepository;
import com.apeng.smartcanteenbackend.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderController(DishRepository dishRepository, OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    private String addOrder(@RequestBody String body, Principal currentUser) {
        OrderAddingRequest orderAddingRequest = JSON.parseObject(body, OrderAddingRequest.class);
        List<Dish> orderedDishes = new ArrayList<>();
        dishRepository.findAllById(orderAddingRequest.dishIds()).forEach(orderedDishes::add);
        Order orderAdded = new Order(null, orderedDishes, userRepository.findById(currentUser.getName()).get());
        orderRepository.save(orderAdded);
        return "Adding order successfully: " + orderAdded;
    }

}
