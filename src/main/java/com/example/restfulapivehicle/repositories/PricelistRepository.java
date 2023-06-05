package com.example.restfulapivehicle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.Pricelist;

public interface PricelistRepository extends JpaRepository<Pricelist, Integer> {
    
}
