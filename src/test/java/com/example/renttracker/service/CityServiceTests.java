package com.example.renttracker.service;

import com.example.renttracker.entity.City;
import com.example.renttracker.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CityServiceTests {
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    // Test cities
    private City munich;
    private City berlin;
    private City augsburg;

    @BeforeEach
    void setUp() {
        munich = new City("Munich", "Bavaria");
        augsburg = new City("Augsburg", "Bavaria");
        berlin = new City("Berlin", "Berlin");
    }

    @Test
    void testGetAllCities() {
        List<City> cities = Arrays.asList(munich, berlin);
        when(cityRepository.findAll()).thenReturn(cities);

        assertEquals(cities, cityService.getAllCities());
    }

    @Test
    void testGetCityByName() {
        when(cityRepository.findByName("Munich")).thenReturn(munich);
        assertEquals(munich, cityService.getCityByName("Munich").orElse(null));
    }

    @Test
    void testGetCityByState() {
        List<City> cities = Arrays.asList(munich, augsburg);
        when(cityRepository.findByState("Bavaria")).thenReturn(cities);
        assertEquals(cities, cityService.getCitiesByState("Bavaria"));
    }
}
