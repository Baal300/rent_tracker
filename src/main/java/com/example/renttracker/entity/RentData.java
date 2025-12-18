package com.example.renttracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rent_data")
public class RentData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", nullable = false)
    @JsonIgnore
    private City city;

    @JsonProperty("cityId")
    public Long getCityId() {
        return city != null ? city.getId() : null;
    }

    @Column(name = "rent_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal rentPrice;

    @Column(name = "apartment_size", nullable = false)
    private Integer apartmentSize;

    @Column(name = "data_date")
    private LocalDate date;

    public RentData() {
    }

    public RentData(City city, BigDecimal rentPrice, Integer apartmentSize, LocalDate date) {
        this.city = city;
        this.rentPrice = rentPrice;
        this.apartmentSize = apartmentSize;
        this.date = date;
    }

    public Long getId() {
        return id;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BigDecimal getAvgRentPrice() {
        return rentPrice;
    }

    public void setAvgRentPrice(BigDecimal avgRentPrice) {
        this.rentPrice = avgRentPrice;
    }

    public Integer getApartmentSize() {
        return apartmentSize;
    }

    public void setApartmentSize(Integer apartmentSize) {
        this.apartmentSize = apartmentSize;
    }

    public LocalDate getDataDate() {
        return date;
    }

    public void setDataDate(LocalDate dataDate) {
        this.date = dataDate;
    }
}
