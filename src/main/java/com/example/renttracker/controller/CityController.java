package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        System.out.println("Cities requested");
        return cityService.getAllCities();
    }

}