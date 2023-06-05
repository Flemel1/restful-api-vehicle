package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class VehicleTypeValidation {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Integer brand_id;
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
}
