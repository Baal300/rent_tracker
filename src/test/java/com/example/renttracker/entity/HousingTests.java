package com.example.renttracker.entity;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HousingTests {
    @Test
    public void testGetRentPerSqm() {
        Housing housing = new Housing(BigDecimal.valueOf(1200), 25);

        assertEquals(
                0,
                BigDecimal.valueOf(48).compareTo(housing.getRentPerSqm()),
                "Rent per sqm should be correctly calculated"
        );
    };

    @Test
    public void testSetRentCostWithInteger() {
        Housing housing = new Housing(BigDecimal.valueOf(1000), 20);
        housing.setRentCost(1500);

        assertEquals(
                0,
                BigDecimal.valueOf(75).compareTo(housing.getRentPerSqm()),
                "Rent per sqm should be updated correctly after changing rent cost"
        );

        assertEquals(
                0,
                BigDecimal.valueOf(1500).compareTo(housing.getRentCost()),
                "Rent cost should be updated correctly after setting"
        );
    }

    @Test
    public void testSetRentCostWithDouble() {
        Housing housing = new Housing(BigDecimal.valueOf(1000), 20);
        housing.setRentCost(1500.00d);

        assertEquals(
                0,
                BigDecimal.valueOf(75).compareTo(housing.getRentPerSqm()),
                "Rent per sqm should be updated correctly after changing rent cost"
        );

        assertEquals(
                0,
                BigDecimal.valueOf(1500).compareTo(housing.getRentCost()),
                "Rent cost should be updated correctly after setting"
        );
    }

    @Test
    public void testSetApartmentSize() {
        Housing housing = new Housing(BigDecimal.valueOf(1000), 20);
        housing.setApartmentSize(25);

        assertEquals(
                0,
                BigDecimal.valueOf(40).compareTo(housing.getRentPerSqm()),
                "Rent per sqm should be updated correctly after changing apartment size"
        );

        assertEquals(
                25,
                housing.getApartmentSize(),
                "Apartment size should be updated correctly after setting"
        );
    }
}
