package com.example.renttracker.config;


import com.example.renttracker.entity.City;
import com.example.renttracker.entity.Housing;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void run(String... args) {
        City munich = new City("Munich", "Bavaria");
        City berlin = new City("Berlin", "Berlin");
        City hamburg = new City("Hamburg", "Hamburg");

        Housing rd = new Housing(munich, BigDecimal.valueOf(450), 18, LocalDate.of(2025, 11, 11));

        entityManager.persist(munich);
        entityManager.persist(berlin);
        entityManager.persist(hamburg);

        entityManager.persist(rd);
    }
}
