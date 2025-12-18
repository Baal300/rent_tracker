package com.example.renttracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Rent Tracker is running!";
    }
}