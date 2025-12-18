package com.example.renttracker.controller;

import com.example.renttracker.entity.RentData;
import com.example.renttracker.repository.RentDataRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RentDataController {
    private final RentDataRepository rentDataRepository;

    public RentDataController(RentDataRepository rentDataRepository) {
        this.rentDataRepository = rentDataRepository;
    }


    @GetMapping("/rentdata")
    public List<RentData> getAllRentData() {
        System.out.println("Rent data requested");
        return rentDataRepository.findAll();
    }
}
