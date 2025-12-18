package com.example.renttracker.repository;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.RentData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RentDataRepositoryTests {
    @Autowired
    private RentDataRepository rentDataRepository;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void findByCityTest() {
        City city = new City("Munich", "Bavaria");
        RentData rentData1 = new RentData(
                city, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11)
        );
        RentData rentData2 = new RentData(
                city, BigDecimal.valueOf(500), 20, LocalDate.of(2025, 12, 11)
        );

        cityRepository.save(city);
        rentDataRepository.save(rentData1);
        rentDataRepository.save(rentData2);

        List<RentData> rentDataList = rentDataRepository.findByCity(city);

        assertEquals(2, rentDataList.size(), "Should find two rent data entries for the city");
        assertTrue(rentDataList.contains(rentData1), "Should contain the first rent data entry");
        assertTrue(rentDataList.contains(rentData2), "Should contain the second rent data entry");
    }

    @Test
    void findByCityNameTest() {
        City city = new City("Berlin", "Berlin");
        RentData rentData1 = new RentData(
                city, BigDecimal.valueOf(600), 25, LocalDate.of(2025, 10, 10)
        );
        RentData rentData2 = new RentData(
                city, BigDecimal.valueOf(650), 30, LocalDate.of(2025, 11, 10)
        );

        cityRepository.save(city);
        rentDataRepository.save(rentData1);
        rentDataRepository.save(rentData2);

        List<RentData> rentDataList = rentDataRepository.findByCityName("Berlin");

        assertEquals(2, rentDataList.size(), "Should find two rent data entries for the city");
        assertTrue(rentDataList.contains(rentData1), "Should contain the first rent data entry");
        assertTrue(rentDataList.contains(rentData2), "Should contain the second rent data entry");
    }

    @Test
    void findAverageRentPriceByCityNameTest() {
        City city = new City("Berlin", "Berlin");
        RentData rentData1 = new RentData(
                city, BigDecimal.valueOf(700), 28, LocalDate.of(2025, 9, 9)
        );
        RentData rentData2 = new RentData(
                city, BigDecimal.valueOf(800), 32, LocalDate.of(2025, 10, 9)
        );

        cityRepository.save(city);
        rentDataRepository.save(rentData1);
        rentDataRepository.save(rentData2);
        BigDecimal averageRent = rentDataRepository.findAverageRentPriceByCityName("Berlin");
        assertEquals(0, BigDecimal.valueOf(750).compareTo(averageRent), "Average rent price should be 750 for Berlin");
    }
}

