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

import com.dmsc.back_end.dto.SupplierDTO;
import com.dmsc.back_end.service.SupplierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SupplierController {
    

    @Autowired
    SupplierService supplierService;

    // Get all Suppliers
    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    // Get supplier by ID
    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }


    // Search Suppliers by name
    @GetMapping("/search")
    public List<SupplierDTO> searchSuppliers(@RequestParam String name) {
        return supplierService.searchSuppliers(name);
    }

    // Create Supplier
    @PostMapping
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO){
        SupplierDTO saved = supplierService.createSupplier(supplierDTO);
        return new ResponseEntity<>(saved,HttpStatus.CREATED);
    }

    // Update Supplier
    @PutMapping("/{id}")
    public SupplierDTO updateSupplier(@PathVariable Integer id, @RequestBody SupplierDTO supplierDTO){
        return supplierService.updateSupplier(id,supplierDTO);
    }

    // Delete Supplier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Integer id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.ok().build();
    }
}
