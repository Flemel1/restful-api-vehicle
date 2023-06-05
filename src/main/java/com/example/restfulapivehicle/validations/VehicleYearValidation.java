package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class VehicleYearValidation {
    private int id;
    @NotNull
    private String year;

    public int getId() {
        return id;
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
