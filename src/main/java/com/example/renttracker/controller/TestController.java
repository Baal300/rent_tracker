package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.repository.CityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final CityRepository cityRepository;

    public TestController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Rent Tracker is running!";
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
}