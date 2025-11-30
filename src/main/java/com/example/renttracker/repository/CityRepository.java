package com.example.renttracker.repository;


import com.example.renttracker.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    City findByName(String name);
    List<City> findByState(String name);
}
