package com.example.restfulapivehicle.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface RestControllerImpl {
    @GetMapping
    public ResponseEntity<Object> index(@RequestParam(defaultValue = "0") int page);
}
