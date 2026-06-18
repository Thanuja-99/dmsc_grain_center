package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.CustomerOrderDTO;
import com.dmsc.back_end.service.CustomerOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer-orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    // GET ALL
    @GetMapping
    public List<CustomerOrderDTO> getAll() {
        return customerOrderService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerOrderDTO getById(@PathVariable Integer id) {
        return customerOrderService.getById(id);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CustomerOrderDTO> create(@RequestBody CustomerOrderDTO dto) {
        CustomerOrderDTO saved = customerOrderService.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerOrderDTO update(@PathVariable Integer id, @RequestBody CustomerOrderDTO dto) {
        return customerOrderService.update(id, dto);
    }

    // DELETE (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerOrderService.delete(id);
        return ResponseEntity.ok().build();
    }
}