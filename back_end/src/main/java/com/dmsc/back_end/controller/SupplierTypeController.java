package com.dmsc.back_end.controller;

import java.util.List;
import java.util.function.Supplier;

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

import com.dmsc.back_end.dto.DriverTypeDTO;
import com.dmsc.back_end.dto.SupplierTypeDTO;
import com.dmsc.back_end.service.SupplierTypeService;

@RestController
@RequestMapping("/api/supplier-types")
@CrossOrigin(origins = "*")
public class SupplierTypeController {
    
    @Autowired
    private SupplierTypeService supplierTypeService;

    // Get All supplier types
    @GetMapping
    public List<SupplierTypeDTO> getAllSupplierTypes() {
        return supplierTypeService.getAllSupplierTypes();
    }

    // Get supplier type by ID
    @GetMapping("/{id}")
    public SupplierTypeDTO getSupplierTypeById(@PathVariable Integer id) {
        return supplierTypeService.getSupplierTypeById(id);
    }

    // Create supplier type
    @PostMapping
    public ResponseEntity<SupplierTypeDTO> createSupplierType(@RequestBody SupplierTypeDTO supplierTypeDTO) {
        SupplierTypeDTO saved = supplierTypeService.saveSupplierType(supplierTypeDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update supplier type
    @PutMapping("/{id}")
    public SupplierTypeDTO updateSupplierType(@PathVariable Integer id, @RequestBody SupplierTypeDTO supplierTypeDTO) {
        return supplierTypeService.updateSupplierType(id, supplierTypeDTO);
    }

    // Delete supplier type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplierType(@PathVariable Integer id) {
        supplierTypeService.deleteSupplierType(id);
        return ResponseEntity.ok().build();
}
}
