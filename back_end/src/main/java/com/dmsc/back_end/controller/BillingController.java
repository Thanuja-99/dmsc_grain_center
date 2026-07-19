package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.BillingDTO;
import com.dmsc.back_end.service.BillingService;

@RestController
@RequestMapping("/api/billings")
@CrossOrigin(origins = "*")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // ================= CREATE =================
    @PostMapping
    public BillingDTO createBilling(@RequestBody BillingDTO billingDTO) {

        return billingService.createBilling(billingDTO);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<BillingDTO> getAllBillings() {

        return billingService.getAllBillings();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public BillingDTO getBillingById(@PathVariable int id) {

        return billingService.getBillingById(id);
    }

    // ================= SEARCH BY BILL ID =================
    @GetMapping("/search/id/{id}")
    public List<BillingDTO> searchBillingById(@PathVariable int id) {

        return billingService.searchBillings(id);
    }

    // ================= SEARCH BY CUSTOMER NAME =================
    @GetMapping("/search/customer/{customerName}")
    public List<BillingDTO> searchBillingByCustomer(
            @PathVariable String customerName) {

        return billingService.searchBillings(customerName);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public BillingDTO updateBilling(
            @PathVariable int id,
            @RequestBody BillingDTO billingDTO) {

        return billingService.updateBilling(id, billingDTO);
    }

    // ================= SOFT DELETE =================
    @DeleteMapping("/{id}")
    public String deleteBilling(@PathVariable int id) {

        billingService.deleteBilling(id);

        return "Billing deleted successfully.";
    }

}