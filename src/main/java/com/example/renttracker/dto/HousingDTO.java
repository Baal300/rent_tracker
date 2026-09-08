package com.example.renttracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


public class HousingDTO {
    private long id;
    private String city;
    private BigDecimal rentCost;
    private BigDecimal rentPerSqm;
    private int apartmentSize;
    private LocalDate date;

    public HousingDTO(
            long id,
            String city,
            BigDecimal rentCost,
            int apartmentSize,
            BigDecimal rentPerSqm,
            LocalDate date
    ) {
        this.id = id;
        this.city = city;
        this.rentCost = rentCost;
        this.apartmentSize = apartmentSize;
        this.rentPerSqm = rentPerSqm;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public BigDecimal getRentCost() {
        return rentCost;
    }

    public int getApartmentSize() {
        return apartmentSize;
    }

    public BigDecimal getRentPerSqm() {
        return rentPerSqm;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HousingDTO))
            return false;
        HousingDTO other = (HousingDTO) o;
        return this.id == other.id &&
                this.city.equals(other.city) &&
                this.rentCost.equals(other.rentCost) &&
                this.apartmentSize == other.apartmentSize &&
                this.rentPerSqm.equals(other.rentPerSqm) &&
                this.date.equals(other.date);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, city, rentCost, apartmentSize, rentPerSqm, date);
    }
}
