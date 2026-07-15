package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.PurchaseOrderStatusDTO;
import com.dmsc.back_end.service.PurchaseOrderStatusService;

@RestController
@RequestMapping("/api/purchase-order-status")
@CrossOrigin(origins = "*")
public class PurchaseOrderStatusController {

    @Autowired
    private PurchaseOrderStatusService purchaseOrderStatusService;

    // ================= GET ALL =================
    @GetMapping
    public List<PurchaseOrderStatusDTO> getAll() {
        return purchaseOrderStatusService.getAll();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public PurchaseOrderStatusDTO getById(@PathVariable int id) {
        return purchaseOrderStatusService.getById(id);
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<PurchaseOrderStatusDTO> create(
            @RequestBody PurchaseOrderStatusDTO purchaseOrderStatusDTO) {

        PurchaseOrderStatusDTO saved = purchaseOrderStatusService.create(purchaseOrderStatusDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public PurchaseOrderStatusDTO update(
            @PathVariable int id,
            @RequestBody PurchaseOrderStatusDTO purchaseOrderStatusDTO) {

        return purchaseOrderStatusService.update(id, purchaseOrderStatusDTO);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {

        purchaseOrderStatusService.delete(id);
        return ResponseEntity.ok().build();
    }
}