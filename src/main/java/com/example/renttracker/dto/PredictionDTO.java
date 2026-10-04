package com.example.renttracker.dto;

import java.math.BigDecimal;

public class PredictionDTO {
    int apartmentSize;
    BigDecimal predictedRent;

    public PredictionDTO(int apartmentSize, BigDecimal predictedRent) {
        this.apartmentSize = apartmentSize;
        this.predictedRent = predictedRent;
    }

    public int getApartmentSize() {
        return apartmentSize;
    }
    public BigDecimal getPredictedRent() {
        return predictedRent;
    }
}
