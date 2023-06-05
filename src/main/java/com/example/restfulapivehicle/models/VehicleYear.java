package com.example.restfulapivehicle.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "vehicle_years")
public class VehicleYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String year;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated_at;
    @JsonManagedReference
    @OneToMany(mappedBy = "vehicle_year", orphanRemoval = true)
    private List<Pricelist> pricelist;

    public List<Pricelist> getPricelist() {
        return pricelist;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
