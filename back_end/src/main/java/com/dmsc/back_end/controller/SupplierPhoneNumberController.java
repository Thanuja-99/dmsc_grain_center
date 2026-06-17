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

import com.dmsc.back_end.service.SupplierPhoneNumberService;
import com.dmsc.back_end.dto.SupplierPhoneNumberDTO;

@RestController
@RequestMapping("/api/supplier-phone-numbers")
@CrossOrigin(origins = "*")
public class SupplierPhoneNumberController {
    
   @Autowired
   SupplierPhoneNumberService supplierPhoneNumberService;

   // Get all phone numbers
    @GetMapping
    public List<SupplierPhoneNumberDTO> getAllPhoneNumbers() {
        return supplierPhoneNumberService.getAllPhoneNumbers();
    }

    // Get phone numbers by supplier ID
    @GetMapping("/supplier/{supplierId}")
    public List<SupplierPhoneNumberDTO> getPhoneNumbersBySupplierId(@PathVariable Integer supplierId) {
        return supplierPhoneNumberService.getPhoneNumbersBySupplierId(supplierId);
    }

    // Create phone number
    @PostMapping
    public ResponseEntity<SupplierPhoneNumberDTO> createPhoneNumber(@RequestBody SupplierPhoneNumberDTO supplierDTO) {
        SupplierPhoneNumberDTO saved = supplierPhoneNumberService.createPhoneNumber(supplierDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update phone number
    @PutMapping("/{id}")
    public SupplierPhoneNumberDTO updatePhoneNumber(@PathVariable Integer id, @RequestBody SupplierPhoneNumberDTO supplierDTO) {
        return supplierPhoneNumberService.updatePhoneNumber(id, supplierDTO);
    }

    // Delete phone number
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoneNumber(@PathVariable Integer id) {
        supplierPhoneNumberService.deletePhoneNumber(id);
        return ResponseEntity.ok().build();
    }

}
