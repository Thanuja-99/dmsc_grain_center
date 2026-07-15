package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.PurchaseOrderItemDTO;
import com.dmsc.back_end.service.PurchaseOrderItemService;

@RestController
@RequestMapping("/api/purchase-order-items")
@CrossOrigin
public class PurchaseOrderItemController {

    @Autowired
    private PurchaseOrderItemService purchaseOrderItemService;

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<PurchaseOrderItemDTO> create(
            @RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) {

        return new ResponseEntity<>(
                purchaseOrderItemService.create(purchaseOrderItemDTO),
                HttpStatus.CREATED
        );
    }

    // ================= GET ALL =================
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItemDTO>> getAll() {
        return ResponseEntity.ok(purchaseOrderItemService.getAll());
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(purchaseOrderItemService.getById(id));
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderItemDTO> update(
            @PathVariable int id,
            @RequestBody PurchaseOrderItemDTO purchaseOrderItemDTO) {

        return ResponseEntity.ok(purchaseOrderItemService.update(id, purchaseOrderItemDTO));
    }

    // ================= DELETE (SOFT DELETE) =================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        purchaseOrderItemService.delete(id);
        return ResponseEntity.ok("Purchase Order Item deleted successfully (soft delete)");
    }
}