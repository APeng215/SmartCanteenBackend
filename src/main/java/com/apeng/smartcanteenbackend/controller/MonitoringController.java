package com.apeng.smartcanteenbackend.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/monitoring")
public class MonitoringController {

    private static int peopleNumber = 0;

    @GetMapping
    private String getInfo() {
        return String.valueOf(peopleNumber);
    }

    @PatchMapping
    private void updatePeopleNumber(@RequestParam int peopleNumber) {
        MonitoringController.peopleNumber = peopleNumber;
    }

}
