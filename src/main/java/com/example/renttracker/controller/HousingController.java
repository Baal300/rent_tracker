package com.example.renttracker.controller;

import com.example.renttracker.entity.Housing;
import com.example.renttracker.service.HousingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@CrossOrigin
@RestController
public class HousingController {
    private final HousingService housingService;

    public HousingController(HousingService housingService) {
        this.housingService = housingService;
    }


    @GetMapping("/housing")
    public List<Housing> getAllHousingDTOs() {
        System.out.println("Housing data requested");
        return housingService.getAllHousing();
    }
}
