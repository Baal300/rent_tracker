package com.example.renttracker.service;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import com.example.renttracker.repository.CityRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HousingServiceTests {
    @Mock
    HousingRepository housingRepository;

    @Mock
    CityRepository cityRepository;

    @InjectMocks
    HousingService housingService;

    // Test data
    City munich;
    City berlin;
    City cologne;
    Housing housing1;
    Housing housing2;
    Housing housing3;

    @BeforeEach
    void setUp() {
        munich = new City("Munich", "Bavaria");
        berlin = new City("Berlin", "Berlin");
        cologne = new City("Cologne", "North Rhine-Westphalia");
        housing1 = new Housing(
                munich,
                BigDecimal.valueOf(450),
                18,
                LocalDate.of(2025, 11, 11)
        );
        housing2 = new Housing(
                berlin,
                BigDecimal.valueOf(600),
                25,
                LocalDate.of(2025, 12, 11)
        );
        housing3 = new Housing(
                munich,
                BigDecimal.valueOf(700),
                35,
                LocalDate.of(2026, 1, 1)
        );

    }

    @Test
    void testGetAllHousing() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);

        when(housingRepository.findAll()).thenReturn(housingList);

        assertEquals(housingList, housingService.getAllHousing());
    }

    @Test
    void getHousingByCityName() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);

        when(housingRepository.findByCityName(munich.getName())).thenReturn(housingList);

        assertEquals(housingList, housingService.getHousingByCity(munich.getName()));
    }

    @Test
    void getHousingByCity() {
        List<Housing> housingList = Arrays.asList(housing1, housing3);

        when(housingRepository.findByCity(munich)).thenReturn(housingList);

        assertEquals(housingList, housingService.getHousingByCity(munich));
    }

    @Test
    void getHousingByCityId() {
        List<Housing> housingList = Arrays.asList(housing1, housing2);

        when(housingRepository.findByCityId(munich.getId())).thenReturn(housingList);

        assertEquals(housingList, housingService.getHousingByCity(munich.getId()));
    }

    @Test
    void testCreateHousing() {
        Housing expectedHousing = new Housing(
                cologne,
                BigDecimal.valueOf(550),
                21,
                LocalDate.of(2026, 2, 1)
        );

        when(cityRepository.findByName("Cologne")).thenReturn(cologne);
        when(housingRepository.save(any(Housing.class))).thenReturn(expectedHousing);

        assertEquals(
                expectedHousing,
                housingService.createHousing(
                        "Cologne",
                        BigDecimal.valueOf(550),
                        21,
                        LocalDate.of(2026, 2, 1)
                )
        );
    }
}
