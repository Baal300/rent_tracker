package com.example.renttracker;

import com.example.renttracker.entity.City;
import com.example.renttracker.repository.CityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CityRepositoryTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    void testSaveAndFindCity() {
        City city = new City("Munich", "Bavaria");

        cityRepository.save(city);
        Optional<City> foundCity = Optional.ofNullable(cityRepository.findByName("Munich"));
        assertTrue(foundCity.isPresent(), "City should be found");
        assertEquals("Bavaria", foundCity.get().getState(), "State should match");
    }

    @Test
    void testFindByState() {
        City nuremberg = new City("Nuremberg", "Bavaria");
        City munich = new City("Munich", "Bavaria");
        City berlin = new City("Berlin", "Berlin");

        cityRepository.save(berlin);
        cityRepository.save(nuremberg);
        cityRepository.save(munich);

        List<City> citiesInBavaria = cityRepository.findByState("Bavaria");


        assertEquals(2, citiesInBavaria.size(), "There should be two cities in Bavaria");
        assertTrue(citiesInBavaria.contains(nuremberg), "Result should contain Nuremberg");
        assertTrue(citiesInBavaria.contains(munich), "Result should contain Munich");
    }

}
