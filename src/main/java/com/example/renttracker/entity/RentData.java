package com.example.renttracker.entity;

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
    private City city;

    @Column(name = "average_rent_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal avgRentPrice;

    @Column(name = "apartment_size", nullable = false)
    private Integer apartmentSize;

    @Column(name = "data_date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_source")
    private DataSource dataSource;

    public RentData() {
    }

    public RentData(City city, BigDecimal avgRentPrice, Integer apartmentSize, LocalDate date, DataSource dataSource) {
        this.city = city;
        this.avgRentPrice = avgRentPrice;
        this.apartmentSize = apartmentSize;
        this.date = date;
        this.dataSource = dataSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public BigDecimal getAvgRentPrice() {
        return avgRentPrice;
    }

    public void setAvgRentPrice(BigDecimal avgRentPrice) {
        this.avgRentPrice = avgRentPrice;
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

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
