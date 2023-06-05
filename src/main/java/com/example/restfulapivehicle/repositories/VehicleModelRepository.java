package com.example.restfulapivehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.VehicleModel;

public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {
    
}
