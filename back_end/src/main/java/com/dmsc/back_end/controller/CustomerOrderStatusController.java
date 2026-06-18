package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.CustomerOrderStatusDTO;
import com.dmsc.back_end.service.CustomerOrderStatusService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer-order-status")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerOrderStatusController {

    @Autowired
    private CustomerOrderStatusService customerOrderStatusService;

    // GET ALL
    @GetMapping
    public List<CustomerOrderStatusDTO> getAll() {
        return customerOrderStatusService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerOrderStatusDTO getById(@PathVariable Integer id) {
        return customerOrderStatusService.getById(id);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CustomerOrderStatusDTO> create(@RequestBody CustomerOrderStatusDTO dto) {
        CustomerOrderStatusDTO saved = customerOrderStatusService.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerOrderStatusDTO update(@PathVariable Integer id, @RequestBody CustomerOrderStatusDTO dto) {
        return customerOrderStatusService.update(id, dto);
    }

    // DELETE (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerOrderStatusService.delete(id);
        return ResponseEntity.ok().build();
    }
}