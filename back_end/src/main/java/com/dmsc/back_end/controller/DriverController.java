package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.dto.DriverDTO;
import com.dmsc.back_end.service.DriverService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DriverController{

    @Autowired
    DriverService driverService;

    // Get all drivers
    @GetMapping
    public List<DriverDTO> getAllDrivers() {
        return driverService.getAllDrivers();
    }

    // Get driver by ID
    @GetMapping("/{id}")
    public DriverDTO getDriverById(@PathVariable Integer id){
        return driverService.getDriverById(id);
    }


    // Search Drivers by name
    @GetMapping("/search")
    public List<DriverDTO> searchDrivers(@RequestParam String name) {
        return driverService.searchDrivers(name);
    }

    // Create Driver
    @PostMapping
    public ResponseEntity<DriverDTO> createDriver(@RequestBody DriverDTO driverDTO){
        DriverDTO saved = driverService.createDriver(driverDTO);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    // Update Driver
    @PutMapping("/{id}")
    public DriverDTO updateDriver(@PathVariable Integer id, @RequestBody DriverDTO driverDTO){
        return driverService.updateDriver(id,driverDTO);
    }

    // Delete Driver
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.ok().build();
    }
    }