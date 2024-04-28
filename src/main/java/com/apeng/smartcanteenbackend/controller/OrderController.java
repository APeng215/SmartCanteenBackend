package com.apeng.smartcanteenbackend.controller;

import com.alibaba.fastjson2.JSON;
import com.apeng.smartcanteenbackend.controller.util.OrderAddingRequest;
import com.apeng.smartcanteenbackend.entity.Dish;
import com.apeng.smartcanteenbackend.entity.Order;
import com.apeng.smartcanteenbackend.entity.User;
import com.apeng.smartcanteenbackend.repository.DishRepository;
import com.apeng.smartcanteenbackend.repository.OrderRepository;
import com.apeng.smartcanteenbackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

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
        Order orderAdded = new Order(null, orderedDishes, getCurrentUser(currentUser));
        orderRepository.save(orderAdded);
        return "Adding order successfully: " + orderAdded;
    }

    private User getCurrentUser(Principal currentUser) {
        return userRepository.findById(currentUser.getName()).get();
    }

    @GetMapping
    private String getOrders(Principal currentUser) {
        List<Order> orders = findOrdersByUser(currentUser);
        hideUserInfo(orders);
        return JSON.toJSONString(orders);
    }

    private List<Order> findOrdersByUser(Principal currentUser) {
        return orderRepository.findByUser(getCurrentUser(currentUser));
    }

    private static void hideUserInfo(List<Order> orders) {
        orders.forEach(order -> {
            order.setUser(null);
        });
    }

}
