package com.example.restfulapivehicle.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulapivehicle.models.EnumRole;
import com.example.restfulapivehicle.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(EnumRole name);
}
