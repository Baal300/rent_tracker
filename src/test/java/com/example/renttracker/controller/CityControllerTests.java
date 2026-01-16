package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityControllerTests {
    @Mock
    private CityService cityService;

    @InjectMocks
    private CityController cityController;

    private City munich;
    private City berlin;

    @BeforeEach
    void setUp() {
        munich = new City("Munich", "Bavaria");
        berlin = new City("Berlin", "Berlin");
    }

    @Test
    void testGetAllCities() {
        List<City> cities = Arrays.asList(munich, berlin);
        when(cityService.getAllCities()).thenReturn(cities);

        List<City> result = cityController.getAllCities();

        assertEquals(2, result.size(), "Should return 2 cities");
        assertEquals("Munich", result.get(0).getName(), "First city should be Munich");
        assertEquals("Berlin", result.get(1).getName(), "Second city should be Berlin");

        // Verify that findAll was called exactly once
        verify(cityService, times(1)).getAllCities();
    }
}