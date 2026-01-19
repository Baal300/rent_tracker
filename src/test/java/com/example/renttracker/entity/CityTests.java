package com.example.renttracker.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CityTests {
    private City city;

    @BeforeEach
    void setUp() {
        city = new City("Munich", "Bavaria");
    }

    @Test
    void testAddHousing() {
        Housing housing1 = new Housing(city, BigDecimal.valueOf(800), 50, LocalDate.of(2025, 12, 1));
        Housing housing2 = new Housing(city, BigDecimal.valueOf(850), 55, LocalDate.of(2026, 1, 1));
        city.addHousing(housing1);
        city.addHousing(housing2);
        List<Housing> expectedHousingList = new java.util.ArrayList<>();
        expectedHousingList.add(housing1);
        expectedHousingList.add(housing2);

        assertEquals(2, city.getHousingList().size(), "City should have a RentData entry");
        assertEquals(expectedHousingList, city.getHousingList(), "RentData list should match the added entries");
        assertEquals(city, city.getHousingList().get(0).getCity(), "RentData's city should be set correctly");
    }
}
