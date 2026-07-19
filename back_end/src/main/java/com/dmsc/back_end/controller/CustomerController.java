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

import com.dmsc.back_end.dto.CustomerDTO;
import com.dmsc.back_end.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    

    @Autowired
    private CustomerService customerService;



    // Get all customers
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    // Get customer by ID
    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    // Search customers by name
    @GetMapping("/search")
    public List<CustomerDTO> searchCustomers(@RequestParam String name) {
        return customerService.searchCustomers(name);
    }

    // Create customer
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
        CustomerDTO saved = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update customer
    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable Integer id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    // Delete customer (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }
}
