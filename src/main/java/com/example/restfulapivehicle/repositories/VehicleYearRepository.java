package com.example.restfulapivehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.VehicleYear;

public interface VehicleYearRepository extends JpaRepository<VehicleYear, Integer> {
    
}
