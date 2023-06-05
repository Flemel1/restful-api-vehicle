package com.example.restfulapivehicle.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Pricelist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int year_id;
    @Column(nullable = false)
    private int model_id;
    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime created_at = LocalDateTime.now();
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updated_at;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "year_id", insertable = false, updatable = false, nullable = false)
    private VehicleYear vehicle_year;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "model_id", insertable = false, updatable = false, nullable = false)
    private VehicleModel vehicle_model;

    public VehicleModel getVehicle_model() {
        return vehicle_model;
    }
    public VehicleYear getVehicle_year() {
        return vehicle_year;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getYear_id() {
        return year_id;
    }
    public void setYear_id(int year_id) {
        this.year_id = year_id;
    }
    public int getModel_id() {
        return model_id;
    }
    public void setModel_id(int model_id) {
        this.model_id = model_id;
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
