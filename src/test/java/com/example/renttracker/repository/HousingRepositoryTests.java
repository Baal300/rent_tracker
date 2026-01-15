package com.example.renttracker.repository;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class HousingRepositoryTests {
    @Autowired
    private HousingRepository housingRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findByCityTest() {
        City city = new City("Munich", "Bavaria");
        Housing housing1 = new Housing(
                city, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11)
        );
        Housing housing2 = new Housing(
                city, BigDecimal.valueOf(500), 20, LocalDate.of(2025, 12, 11)
        );

        cityRepository.save(city);
        housingRepository.save(housing1);
        housingRepository.save(housing2);

        List<Housing> housingList = housingRepository.findByCity(city);

        assertEquals(2, housingList.size(), "Should find two rent data entries for the city");
        assertTrue(housingList.contains(housing1), "Should contain the first rent data entry");
        assertTrue(housingList.contains(housing2), "Should contain the second rent data entry");
    }

    @Test
    void findByCityNameTest() {
        City city = new City("Berlin", "Berlin");
        Housing housing1 = new Housing(
                city, BigDecimal.valueOf(600), 25, LocalDate.of(2025, 10, 10)
        );
        Housing housing2 = new Housing(
                city, BigDecimal.valueOf(650), 30, LocalDate.of(2025, 11, 10)
        );

        cityRepository.save(city);
        housingRepository.save(housing1);
        housingRepository.save(housing2);

        List<Housing> housingList = housingRepository.findByCityName("Berlin");

        assertEquals(2, housingList.size(), "Should find two rent data entries for the city");
        assertTrue(housingList.contains(housing1), "Should contain the first rent data entry");
        assertTrue(housingList.contains(housing2), "Should contain the second rent data entry");
    }

    @Test
    void findAverageRentPriceByCityNameTest() {
        City city = new City("Berlin", "Berlin");
        Housing housing1 = new Housing(
                city, BigDecimal.valueOf(700), 28, LocalDate.of(2025, 9, 9)
        );
        Housing housing2 = new Housing(
                city, BigDecimal.valueOf(800), 32, LocalDate.of(2025, 10, 9)
        );

        cityRepository.save(city);
        housingRepository.save(housing1);
        housingRepository.save(housing2);
        BigDecimal averageRent = housingRepository.findAverageRentPriceByCityName("Berlin");
        assertEquals(0, BigDecimal.valueOf(750).compareTo(averageRent), "Average rent price should be 750 for Berlin");
    }
}

