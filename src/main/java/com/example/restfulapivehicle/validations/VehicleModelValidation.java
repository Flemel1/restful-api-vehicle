package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class VehicleModelValidation {
    private int id;
    @NotNull
    private String name;
    @NotNull
    private Integer type_id;
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
    public int getType_id() {
        return type_id;
    }
    public void setType_id(int type_id) {
        this.type_id = type_id;
    }   
}
