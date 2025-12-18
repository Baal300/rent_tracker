package com.example.renttracker.controller;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.RentData;
import com.example.renttracker.repository.RentDataRepository;
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
class RentDataControllerTests {
    @Mock
    private RentDataRepository rentDataRepository;

    @InjectMocks
    private RentDataController rentDataController;

    private RentData rentData1;
    private RentData rentData2;

    @BeforeEach
    void setUp() {
        City munich = new City("Munich", "Bavaria");
        City berlin = new City("Berlin", "Berlin");
        rentData1 = new RentData(munich, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11));
        rentData2 = new RentData(berlin, BigDecimal.valueOf(600), 25, LocalDate.of(2025, 12, 11));
    }

    @Test
    void testGetAllRentData() {
        List<RentData> rentDataList = Arrays.asList(rentData1, rentData2);
        // Mock repository
        when(rentDataRepository. findAll()).thenReturn(rentDataList);

        List<RentData> result = rentDataController.getAllRentData();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(BigDecimal.valueOf(450), result.get(0).getAvgRentPrice());
        assertEquals(BigDecimal.valueOf(600), result.get(1).getAvgRentPrice());

        // Verify that findAll was called exactly once
        verify(rentDataRepository, times(1)).findAll();
    }
}