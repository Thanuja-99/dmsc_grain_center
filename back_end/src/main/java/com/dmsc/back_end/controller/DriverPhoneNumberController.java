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
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.dto.DriverPhoneNumberDTO;
import com.dmsc.back_end.service.DriverPhoneNumberService;


@RestController
@RequestMapping("/api/driver-phone-numbers")
@CrossOrigin(origins = "*")
public class DriverPhoneNumberController{

    @Autowired
    private DriverPhoneNumberService  driverPhoneNumberService;

    // Get all phone numbers
    @GetMapping
    public List<DriverPhoneNumberDTO> getAllPhoneNumbers() {
        return driverPhoneNumberService.getAllPhoneNumbers();
    }

    // Get phone numbers by driver ID
    @GetMapping("/driver/{driverId}")
    public List<DriverPhoneNumberDTO> getPhoneNumbersByDriverId(@PathVariable Integer driverId) {
        return driverPhoneNumberService.getPhoneNumbersByDriverId(driverId);
    }

    // Create phone number
    @PostMapping
    public ResponseEntity<DriverPhoneNumberDTO> createPhoneNumber(@RequestBody DriverPhoneNumberDTO driverDTO) {
        DriverPhoneNumberDTO saved = driverPhoneNumberService.createPhoneNumber(driverDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update phone number
    @PutMapping("/{id}")
    public DriverPhoneNumberDTO updatePhoneNumber(@PathVariable Integer id, @RequestBody DriverPhoneNumberDTO driverDTO) {
        return driverPhoneNumberService.updatePhoneNumber(id, driverDTO);
    }

    // Delete phone number
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable Integer id) {
        driverPhoneNumberService.deletePhoneNumber(id);
        return ResponseEntity.ok().build();
    }
}