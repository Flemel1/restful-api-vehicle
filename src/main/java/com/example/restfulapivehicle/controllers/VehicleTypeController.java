package com.example.restfulapivehicle.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.restfulapivehicle.models.VehicleType;
import com.example.restfulapivehicle.repositories.VehicleTypeRepository;
import com.example.restfulapivehicle.validations.VehicleTypeValidation;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle-type")
public class VehicleTypeController {
    @Autowired
    private VehicleTypeRepository repository;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> fetchAll(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> data = new HashMap<>();
        try {
            List<VehicleType> prices = new ArrayList<>();
            PageRequest paging = PageRequest.of(page - 1, 5);
            Page<VehicleType> pageResult = repository.findAll(paging);
            prices = pageResult.getContent();
            data.put("status_code", 200);
            data.put("data", prices);
            data.put("total", pageResult.getTotalElements());
            data.put("limit", pageResult.getSize());
            data.put("skip", pageResult.getPageable().getOffset());
            return new ResponseEntity<Object>(data, HttpStatus.OK);
        } catch (Exception e) {
            data.put("status_code", 500);
            data.put("message", e.getMessage());
            return new ResponseEntity<Object>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> show(@Nonnull @PathVariable("id") int id) {
        Map<String, Object> data = new HashMap<>();
        try {
            VehicleType entity = repository.findById(id).get();
            data.put("status_code", 200);
            data.put("data", entity);
            return new ResponseEntity<Object>(data, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            data.put("status_code", 404);
            data.put("message", "data not found");
            return new ResponseEntity<Object>(data, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            data.put("status_code", 500);
            data.put("message", e.getMessage());
            return new ResponseEntity<Object>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> create(@Valid VehicleTypeValidation validation, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        VehicleType entity = new VehicleType();
        entity.setBrand_id(validation.getBrand_id());
        entity.setName(validation.getName());
        try {
            repository.save(entity);
            response.put("status_code", 200);
            response.put("message", "data created");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> update(@Valid VehicleTypeValidation validation, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<String, Object>();
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<String, String>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        }
        VehicleType entity = new VehicleType();
        entity.setId(validation.getId());
        entity.setBrand_id(validation.getBrand_id());
        entity.setName(validation.getName());
        entity.setUpdated_at(LocalDateTime.now());
        try {
            repository.save(entity);
            response.put("status_code", 200);
            response.put("message", "data updated");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            VehicleType entity = repository.findById(id).get();
            repository.delete(entity);
            response.put("status_code", 200);
            response.put("message", "data deleted");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (NoSuchElementException e) {

            response.put("status_code", 404);
            response.put("message", "data not found");
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            response.put("status_code", 500);
            response.put("message", e.getMessage());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
