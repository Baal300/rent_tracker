package com.example.renttracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@Table(name = "rent_data")
public class Housing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City city;

    @JsonProperty("cityId")
    public long getCityId() {
        return city != null ? city.getId() : null;
    }

    @Column(name = "rent_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal rentCost;

    @Column(name = "rent_per_sqm", precision = 10, scale = 2)
    private BigDecimal rentPerSqm;

    @Column(name = "apartment_size", nullable = false)
    private int apartmentSize;

    @Column(name = "data_date")
    private LocalDate date;

    private BigDecimal calculateRentPerSqm(BigDecimal rentCost, int apartmentSize) {
        return rentCost.divide(BigDecimal.valueOf(apartmentSize), 2, RoundingMode.DOWN);
    }

    public Housing(BigDecimal rentCost, int apartmentSize) {
        this.rentCost = rentCost;
        this.apartmentSize = apartmentSize;
        this.rentPerSqm = calculateRentPerSqm(rentCost, apartmentSize);
    }

    public Housing(City city, BigDecimal rentCost, int apartmentSize, LocalDate date) {
        this.city = city;
        this.rentCost = rentCost;
        this.apartmentSize = apartmentSize;
        this.date = date;
        this.rentPerSqm = calculateRentPerSqm(rentCost, apartmentSize);
    }

    public long getId() {
        return id;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public int getApartmentSize() {
        return apartmentSize;
    }

    public void setApartmentSize(int apartmentSize) {
        this.apartmentSize = apartmentSize;
        rentPerSqm = calculateRentPerSqm(this.rentCost, apartmentSize);
    }

    public LocalDate getDataDate() {
        return date;
    }
    public void setDataDate(LocalDate dataDate) {
        this.date = dataDate;
    }

    public BigDecimal getRentCost() { return rentCost; }

    public void setRentCost(int rentCost) {
        this.rentCost = BigDecimal.valueOf(rentCost);
        rentPerSqm = calculateRentPerSqm(this.rentCost, apartmentSize);
    }

    public void setRentCost(double rentCost) {
        this.rentCost = BigDecimal.valueOf(rentCost);
        rentPerSqm = calculateRentPerSqm(this.rentCost, apartmentSize);
    }

    public BigDecimal getRentPerSqm() { return rentPerSqm; }
}
