package com.example.renttracker.repository;

import com.example.renttracker.entity.City;
import com.example.renttracker.entity.RentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RentDataRepository extends JpaRepository<RentData, Long> {
    List<RentData> findByCity(City city);
    List<RentData> findByCityName(String cityName);

    // Query to get average rent price for a given city name
    @Query("SELECT AVG(rd.rentPrice) FROM RentData rd WHERE rd.city.name = ?1")
    BigDecimal findAverageRentPriceByCityName(String cityName);
}
