package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.RentData;
import com.example.renttracker.repository.CityRepository;
import com.example.renttracker.repository.RentDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    private final CityRepository cityRepository;
    private final RentDataRepository rentDataRepository;

    public TestController(CityRepository cityRepository, RentDataRepository rentDataRepository) {
        this.cityRepository = cityRepository;
        this.rentDataRepository = rentDataRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Rent Tracker is running!";
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        System.out.println("Cities requested");
        return cityRepository.findAll();
    }

    @GetMapping("/rentdata")
    public List<RentData> getAllRentData() {
        System.out.println("Rent data requested");
        return rentDataRepository.findAll();
    }
}