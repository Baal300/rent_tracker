package com.example.renttracker.controller;

import com.example.renttracker.dto.PredictionDTO;
import com.example.renttracker.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PredictionController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final PredictionService predictionService;

    /**
     * Constructor
     */
    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    /**
     * Returns a PredictionDTO on a get request.
     */
    @GetMapping("/prediction")
    public PredictionDTO getPrediction(@Param("apartmentSize") int apartmentSize, @Param("city") String city) {
        logger.info("Prediction requested");
        return predictionService.requestPrediction(apartmentSize, city);
    }
}
