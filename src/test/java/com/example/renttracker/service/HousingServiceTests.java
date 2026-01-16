package com.example.renttracker.service;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import com.example.renttracker.repository.HousingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HousingServiceTests {
    @Mock
    HousingRepository housingRepository;

    @InjectMocks
    HousingService housingService;

    // Test data
    City munich;
    City berlin;
    Housing housing1;
    Housing housing2;

    @BeforeEach
    void setUp() {
        munich = new City("Munich", "Bavaria");
        berlin = new City("Berlin", "Berlin");
        housing1 = new Housing(munich, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11));
        housing2 = new Housing(berlin, BigDecimal.valueOf(600), 25, LocalDate.of(2025, 12, 11));
    }

    @Test
    void testGetAllHousing() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);
        when(housingRepository.findAll()).thenReturn(housingList);
        assertEquals(housingList, housingService.getAllHousing());
    }
}
