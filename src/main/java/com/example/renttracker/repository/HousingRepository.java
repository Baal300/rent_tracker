package com.example.renttracker.repository;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HousingRepository extends JpaRepository<Housing, Long> {
    List<Housing> findByCity(City city);
    List<Housing> findByCityName(String cityName);

    // Query to get average rent price for a given city name
    @Query("SELECT AVG(housing.rentCost) FROM Housing housing WHERE housing.city.name = ?1")
    BigDecimal findAverageRentPriceByCityName(String cityName);


}
