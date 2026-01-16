package com.example.renttracker.service;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import com.example.renttracker.repository.CityRepository;
import com.example.renttracker.repository.HousingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class HousingService {
    private final HousingRepository housingRepository;
    private final CityRepository cityRepository;

    public HousingService(HousingRepository housingRepository, CityRepository cityRepository) {
        this.housingRepository = housingRepository;
        this.cityRepository = cityRepository;
    }

    public List<Housing> getAllHousing() {
        return housingRepository.findAll();
    }

    public Housing getHousingById(long id) {
        return housingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Housing with ID " + id + " not found"));
    }

    // By city name
    public List<Housing> getHousingByCity(String cityName) {
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }
        return housingRepository.findByCityName(cityName);
    }

    // By city object
    public List<Housing> getHousingByCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("City cannot be null");
        }
        return housingRepository.findByCity(city);
    }

    @Transactional
    public Housing createHousing(String cityName, BigDecimal rentCost, int apartmentSize, LocalDate date) {
        // Validate inputs
        if (cityName == null || cityName.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }
        if (rentCost == null || rentCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Rent cost must be positive");
        }
        if (apartmentSize <= 0) {
            throw new IllegalArgumentException("Apartment size must be positive");
        }

        // Find the city
        City city = cityRepository.findByName(cityName);
        if (city == null) {
            throw new IllegalArgumentException("City with name '" + cityName + "' not found");
        }

        // Create housing entry
        Housing housing = new Housing(city, rentCost, apartmentSize, date);
        return housingRepository.save(housing);
    }
}
