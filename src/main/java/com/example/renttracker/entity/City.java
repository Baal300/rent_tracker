package com.example.renttracker.entity;
import jakarta.persistence.*;

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

}
