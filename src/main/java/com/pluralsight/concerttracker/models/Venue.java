package com.pluralsight.concerttracker.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Venue {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String city;
    private Long capacity;

    public Venue() {
    }

    public Venue(String name, String city, Long capacity) {
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getCapacity() {
        return this.capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }
}
