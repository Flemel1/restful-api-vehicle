package com.example.restfulapivehicle.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "vehicle_types")
public class VehicleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int brand_id;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated_at;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "brand_id", insertable = false, updatable = false, nullable = false)
    private VehicleBrand vehicle_brand;
    @JsonManagedReference
    @OneToMany(mappedBy = "vehicle_type", orphanRemoval = true)
    private List<VehicleModel> vehicle_model;

    public VehicleBrand getVehicle_brand() {
        return vehicle_brand;
    }
    public List<VehicleModel> getVehicle_model() {
        return vehicle_model;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBrand_id() {
        return brand_id;
    }
    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
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

    
}
