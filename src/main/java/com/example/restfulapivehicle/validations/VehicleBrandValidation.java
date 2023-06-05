package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class VehicleBrandValidation {
    private int id;
    @NotNull
    private String name;
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
}
