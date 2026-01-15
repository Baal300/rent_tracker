package com.example.renttracker.controller;

import com.example.renttracker.entity.Housing;
import com.example.renttracker.repository.HousingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HousingController {
    private final HousingRepository housingRepository;

    public HousingController(HousingRepository housingRepository) {
        this.housingRepository = housingRepository;
    }


    @GetMapping("/housing")
    public List<Housing> getAllHousingData() {
        System.out.println("Housing data requested");
        return housingRepository.findAll();
    }
}
