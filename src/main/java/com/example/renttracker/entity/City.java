package com.example.renttracker.entity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String state;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RentData> rentDataList = new ArrayList<>();


    public City() {}
    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this. name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public List<RentData> getRentDataList() { return rentDataList; }
    public void setRentDataList(List<RentData> rentDataList) { this.rentDataList = rentDataList; }
    public void addRentData(RentData rentData) {
        this.rentDataList.add(rentData);
        rentData.setCity(this);
    }

}
