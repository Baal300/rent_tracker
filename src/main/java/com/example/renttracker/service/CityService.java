package com.example.renttracker.service;

import com.example.renttracker.entity.City;
import com.example.renttracker.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public Optional<City> getCityByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }
        return Optional.ofNullable(cityRepository.findByName(name));
    }

    public List<City> getCitiesByState(String state) {
        if (state == null || state.trim().isEmpty()) {
            throw new IllegalArgumentException("State name cannot be null or empty");
        }
        return cityRepository.findByState(state);
    }
}
