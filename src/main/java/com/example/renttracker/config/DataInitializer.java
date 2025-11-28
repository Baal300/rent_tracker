package com.example.renttracker.config;


import com.example.renttracker.entity.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public void run(String... args) throws Exception {
        City munich = new City("München", "Bayern");
        City berlin = new City("Berlin", "Berlin");
        City hamburg = new City("Hamburg", "Hamburg");

        entityManager.persist(munich);
        entityManager.persist(berlin);
        entityManager.persist(hamburg);
    }
}
