package com.apeng.smartcanteenbackend.controller;

import com.alibaba.fastjson2.JSON;
import com.apeng.smartcanteenbackend.controller.util.OrderAddingRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 仅用于测试 API
 */
@RestController
@RequestMapping("/login")
public class LoginController {


    @GetMapping
    private String loginTest() {
        return "Logged in";
    }


}
