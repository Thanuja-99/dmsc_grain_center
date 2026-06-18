package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.CustomerOrderItemDTO;
import com.dmsc.back_end.service.CustomerOrderItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer-order-items")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerOrderItemController {

    @Autowired
    private CustomerOrderItemService customerOrderItemService;

    // GET ALL
    @GetMapping
    public List<CustomerOrderItemDTO> getAll() {
        return customerOrderItemService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerOrderItemDTO getById(@PathVariable Integer id) {
        return customerOrderItemService.getById(id);
    }

    // CREATE (stock reduce logic already in service)
    @PostMapping
    public ResponseEntity<CustomerOrderItemDTO> create(@RequestBody CustomerOrderItemDTO dto) {
        CustomerOrderItemDTO saved = customerOrderItemService.create(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerOrderItemDTO update(@PathVariable Integer id, @RequestBody CustomerOrderItemDTO dto) {
        return customerOrderItemService.update(id, dto);
    }

    // DELETE (soft delete + stock restore in service)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerOrderItemService.delete(id);
        return ResponseEntity.ok().build();
    }
}