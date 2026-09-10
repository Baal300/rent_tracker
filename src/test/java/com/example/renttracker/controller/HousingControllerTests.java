package com.example.renttracker.controller;

import com.example.renttracker.dto.HousingDTO;
import com.example.renttracker.dto.Mapper;
import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import com.example.renttracker.service.HousingService;
import org. junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org. mockito.Mock;
import org.mockito.junit.jupiter. MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito. Mockito.*;

@ExtendWith(MockitoExtension.class)
class HousingControllerTests {
    @Mock
    private HousingService housingService;

    private HousingController housingController;

    private Housing housing1;
    private Housing housing2;
    private HousingDTO housingDto1;
    private HousingDTO housingDto2;


    @BeforeEach
    void setUp() {
        housingController = new HousingController(housingService, new Mapper());
        City munich = new City("Munich", "Bavaria");
        City berlin = new City("Berlin", "Berlin");
        housing1 = new Housing(
                1,
                munich,
                BigDecimal.valueOf(450),
                18,
                LocalDate.of(2025, 11, 11)
        );
        housing2 = new Housing(
                2,
                berlin,
                BigDecimal.valueOf(600),
                25,
                LocalDate.of(2025, 12, 11)
        );
        housingDto1 = new HousingDTO(
                1,
                "Munich",
                BigDecimal.valueOf(450),
                18,
                BigDecimal.valueOf(25).setScale(2, RoundingMode.HALF_UP),
                LocalDate.of(2025, 11, 11)
        );
        housingDto2 = new HousingDTO(
                2,
                "Berlin",
                BigDecimal.valueOf(600),
                25,
                BigDecimal.valueOf(24).setScale(2, RoundingMode.HALF_UP),
                LocalDate.of(2025, 12, 11)
        );
    }

    @Test
    void testGetAllHousing() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);
        // Mock service
        when(housingService.getAllHousing()).thenReturn(housingList);

        List<HousingDTO> result = housingController.getAllHousing();

        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "Result size should be 2");
        assertThat(result).containsExactlyInAnyOrder(housingDto1, housingDto2);

        // Verify that findAll was called exactly once
        verify(housingService, times(1)).getAllHousing();
    }
}