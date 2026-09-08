package com.example.renttracker.dto;

import com.example.renttracker.entity.Housing;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    /**
     * Transforms Housing entity to HousingDTO.
     */
    public HousingDTO toDto (Housing housing){
        return new HousingDTO(
                housing.getId(),
                housing.getCity().getName(),
                housing.getRentCost(),
                housing.getApartmentSize(),
                housing.getRentPerSqm(),
                housing.getDataDate()
                );
    }
}
