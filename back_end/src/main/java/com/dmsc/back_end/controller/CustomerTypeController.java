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

import com.dmsc.back_end.dto.CustomerTypeDTO;
import com.dmsc.back_end.service.CustomerTypeService;

@RestController
@RequestMapping("/api/customer-types")
@CrossOrigin
public class CustomerTypeController {


    @Autowired
    private CustomerTypeService customerTypeService;

    // Get all customer types
    @GetMapping
    public List<CustomerTypeDTO> getAllCustomerTypes() {
        return customerTypeService.getAllCustomerTypes();
    }

    // Get customer type by ID
    @GetMapping("/{id}")
    public CustomerTypeDTO getCustomerTypeById(@PathVariable Integer id) {
        return customerTypeService.getCustomerTypeById(id);
    }

    // Create customer type
    @PostMapping
    public ResponseEntity<CustomerTypeDTO> createCustomerType(@RequestBody CustomerTypeDTO dto) {
        CustomerTypeDTO saved = customerTypeService.saveCustomerType(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update customer type
    @PutMapping("/{id}")
    public CustomerTypeDTO updateCustomerType(@PathVariable Integer id, @RequestBody CustomerTypeDTO dto) {
        return customerTypeService.updateCustomerType(id, dto);
    }

    // Delete customer type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerType(@PathVariable Integer id) {
        customerTypeService.deleteCustomerType(id);
        return ResponseEntity.ok().build();
    }
}
