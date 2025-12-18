package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.repository.CityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {
    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping("/cities")
    public List<City> getAllCities() {
        System.out.println("Cities requested");
        return cityRepository.findAll();
    }

}