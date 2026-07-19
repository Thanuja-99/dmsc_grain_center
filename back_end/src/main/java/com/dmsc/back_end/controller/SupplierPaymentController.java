package com.dmsc.back_end.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.SupplierPaymentDTO;
import com.dmsc.back_end.service.SupplierPaymentService;

@RestController
@RequestMapping("/api/supplier-payments")
@CrossOrigin(origins = "*")
public class SupplierPaymentController {

    @Autowired
    private SupplierPaymentService supplierPaymentService;

    // ================= CREATE =================
    @PostMapping
    public SupplierPaymentDTO create(@RequestBody SupplierPaymentDTO dto) {

        return supplierPaymentService.create(dto);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public SupplierPaymentDTO update(@PathVariable int id,@RequestBody SupplierPaymentDTO dto) {

        return supplierPaymentService.update(id, dto);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {

        supplierPaymentService.delete(id);

        return "Supplier Payment deleted successfully.";
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public SupplierPaymentDTO getById(@PathVariable int id) {

        return supplierPaymentService.getById(id);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<SupplierPaymentDTO> getAll() {

        return supplierPaymentService.getAll();
    }

    // ================= SEARCH BY GRN =================
    @GetMapping("/search/grn/{grnId}")
    public List<SupplierPaymentDTO> searchByGrn(@PathVariable Integer grnId) {

        return supplierPaymentService.searchByGrn(grnId);
    }

    // ================= SEARCH BY PAYMENT DATE =================
    @GetMapping("/search/date/{paymentDate}")
    public List<SupplierPaymentDTO> searchByDate(@PathVariable LocalDate paymentDate) {

        return supplierPaymentService.searchByDate(paymentDate);
    }

}