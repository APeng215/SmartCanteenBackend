package com.apeng.smartcanteenbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仅用于测试 API
 */
@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {


    @GetMapping
    public String login() {
        return "Login";
    }


}
