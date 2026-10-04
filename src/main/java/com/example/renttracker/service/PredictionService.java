package com.example.renttracker.service;

import com.example.renttracker.dto.PredictionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class PredictionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final RestClient mlService;

    /**
     * Creates PredictionService with injected url of the machine learning service.
     */
    public PredictionService(@Value("${ml-service.url}") String mlServiceUrl) {
        this.mlService = RestClient.builder()
                .baseUrl(mlServiceUrl)
                .build();
    }

    /**
     * Requests a prediction from the machine learning service.
     */
    public PredictionDTO requestPrediction(int apartmentSize, String city) {
        logger.info("Requesting prediction for city: {}", city);
        PredictionDTO response = mlService.get()
                .uri("/prediction?apartmentSize={apartmentsSize}&city={city}", apartmentSize, city)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(PredictionDTO.class);
        logger.info("Received prediction: {}", response);
        return response;
    }
}
