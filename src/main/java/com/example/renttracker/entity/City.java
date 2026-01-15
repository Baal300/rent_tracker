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
    private List<Housing> housingList = new ArrayList<>();


    public City() {}
    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this. name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public List<Housing> getRentDataList() { return housingList; }
    public void setRentDataList(List<Housing> housingList) { this.housingList = housingList; }

    public void addRentData(Housing housing) {
        this.housingList.add(housing);
        housing.setCity(this);
    }

}
