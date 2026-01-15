package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import com.example.renttracker.repository.HousingRepository;
import org. junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org. mockito.Mock;
import org.mockito.junit.jupiter. MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito. Mockito.*;

@ExtendWith(MockitoExtension.class)
class HousingControllerTests {
    @Mock
    private HousingRepository housingRepository;

    @InjectMocks
    private HousingController housingController;

    private Housing housing1;
    private Housing housing2;

    @BeforeEach
    void setUp() {
        City munich = new City("Munich", "Bavaria");
        City berlin = new City("Berlin", "Berlin");
        housing1 = new Housing(munich, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11));
        housing2 = new Housing(berlin, BigDecimal.valueOf(600), 25, LocalDate.of(2025, 12, 11));
    }

    @Test
    void testGetAllRentData() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);
        // Mock repository
        when(housingRepository. findAll()).thenReturn(housingList);

        List<Housing> result = housingController.getAllHousingData();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(BigDecimal.valueOf(450), result.get(0).getRentCost());
        assertEquals(BigDecimal.valueOf(600), result.get(1).getRentCost());

        // Verify that findAll was called exactly once
        verify(housingRepository, times(1)).findAll();
    }
}