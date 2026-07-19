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

import com.dmsc.back_end.dto.CustomerPhoneNumberDTO;
import com.dmsc.back_end.service.CustomerPhoneNumberService;

@RestController
@RequestMapping("/api/phone-numbers")
@CrossOrigin(origins = "*")
public class CustomerPhoneNumberController {
    
    @Autowired
    private CustomerPhoneNumberService phoneNumberService;

    // Get all phone numbers
    @GetMapping
    public List<CustomerPhoneNumberDTO> getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    // Get phone numbers by customer ID
    @GetMapping("/customer/{customerId}")
    public List<CustomerPhoneNumberDTO> getPhoneNumbersByCustomerId(@PathVariable Integer customerId) {
        return phoneNumberService.getPhoneNumbersByCustomerId(customerId);
    }

    // Create phone number
    @PostMapping
    public ResponseEntity<CustomerPhoneNumberDTO> createPhoneNumber(@RequestBody CustomerPhoneNumberDTO customerDTO) {
        CustomerPhoneNumberDTO saved = phoneNumberService.createPhoneNumber(customerDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update phone number
    @PutMapping("/{id}")
    public CustomerPhoneNumberDTO updatePhoneNumber(@PathVariable Integer id, @RequestBody CustomerPhoneNumberDTO customerDTO) {
        return phoneNumberService.updatePhoneNumber(id, customerDTO);
    }

    // Delete phone number
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable Integer id) {
        phoneNumberService.deletePhoneNumber(id);
        return ResponseEntity.ok().build();
    }
}
