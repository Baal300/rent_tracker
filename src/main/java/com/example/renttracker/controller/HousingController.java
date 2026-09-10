package com.example.renttracker.controller;

import com.example.renttracker.dto.CreateHousingDTO;
import com.example.renttracker.dto.HousingDTO;
import com.example.renttracker.dto.Mapper;
import com.example.renttracker.service.HousingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static java.util.stream.Collectors.toList;

@CrossOrigin
@RestController
public class HousingController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final HousingService housingService;
    private final Mapper mapper;

    /**
     * Constructor
     */
    public HousingController(HousingService housingService, Mapper mapper) {
        this.housingService = housingService;
        this.mapper = mapper;
    }

    /**
     * Returns a list of all HousingDTOs on a get request.
     */
    @GetMapping("/housing")
    public List<HousingDTO> getAllHousing() {
        logger.info("Housing data requested");
        return housingService.getAllHousing()
                .stream()
                .map(mapper::toDto)
                .collect(toList());
    }

    /**
     * Creates a Housing entry on a post request.
     */
    @PostMapping("/housing")
    public ResponseEntity<HousingDTO> createHousing(@RequestBody CreateHousingDTO createHousingDTO) {
        logger.info("Housing data creation requested");
        return ResponseEntity
                .status(201)
                .body(mapper.toDto(housingService.createHousing(createHousingDTO)));
    }
}
