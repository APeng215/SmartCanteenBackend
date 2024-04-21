package com.apeng.smartcanteenbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {


    @GetMapping
    public String login() {
        return "Login";
    }


}
