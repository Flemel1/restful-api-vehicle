package com.example.restfulapivehicle.validations;

import jakarta.validation.constraints.NotNull;

public class PricelistValidation {
    private int id;
    @NotNull
    private int year_id;
    @NotNull
    private int model_id;
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
}
