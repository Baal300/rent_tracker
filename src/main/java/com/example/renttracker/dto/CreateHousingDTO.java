package com.example.renttracker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class CreateHousingDTO {
    private String city;
    private BigDecimal rentCost;
    private int apartmentSize;
    private LocalDate date;

    public CreateHousingDTO(
            String city,
            BigDecimal rentCost,
            int apartmentSize,
            LocalDate date
    ) {
        this.city = city;
        this.rentCost = rentCost;
        this.apartmentSize = apartmentSize;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CreateHousingDTO))
            return false;
        CreateHousingDTO other = (CreateHousingDTO) o;
        return this.city.equals(other.city) &&
                this.rentCost.equals(other.rentCost) &&
                this.apartmentSize == other.apartmentSize &&
                this.date.equals(other.date);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(city, rentCost, apartmentSize, date);
    }
}
