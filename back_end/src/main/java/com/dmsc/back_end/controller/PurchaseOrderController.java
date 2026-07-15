package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.PurchaseOrderDTO;
import com.dmsc.back_end.service.PurchaseOrderService;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> create(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        return new ResponseEntity<>(purchaseOrderService.create(purchaseOrderDTO), HttpStatus.CREATED);
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<PurchaseOrderDTO>> getAll() {
        return ResponseEntity.ok(purchaseOrderService.getAll());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(purchaseOrderService.getById(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderDTO> update(
            @PathVariable int id,
            @RequestBody PurchaseOrderDTO purchaseOrderDTO) {

        return ResponseEntity.ok(purchaseOrderService.update(id, purchaseOrderDTO));
    }

    // ================= DELETE (SOFT DELETE) =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        purchaseOrderService.delete(id);
        return ResponseEntity.ok("Purchase Order deleted successfully (soft delete)");
    }
}