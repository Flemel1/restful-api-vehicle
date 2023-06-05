package com.example.restfulapivehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.VehicleType;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {

}
