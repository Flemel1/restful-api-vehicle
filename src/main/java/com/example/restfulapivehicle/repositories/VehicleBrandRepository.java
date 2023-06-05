package com.example.restfulapivehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.VehicleBrand;

public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Integer> {
    
}
