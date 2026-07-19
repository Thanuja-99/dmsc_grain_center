package com.dmsc.back_end.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.CustomerPaymentDTO;
import com.dmsc.back_end.service.CustomerPaymentService;

@RestController
@RequestMapping("/api/customer-payments")
@CrossOrigin(origins = "*")
public class CustomerPaymentController {

    @Autowired
    private CustomerPaymentService customerPaymentService;

    // ================= CREATE =================
    @PostMapping
    public CustomerPaymentDTO create(@RequestBody CustomerPaymentDTO dto) {

        return customerPaymentService.create(dto);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public CustomerPaymentDTO update(@PathVariable int id,@RequestBody CustomerPaymentDTO dto) {

        return customerPaymentService.update(id, dto);
    }

    // ================= DELETE (SOFT DELETE) =================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {

        customerPaymentService.delete(id);

        return "Customer Payment deleted successfully.";
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public CustomerPaymentDTO getById(@PathVariable int id) {

        return customerPaymentService.getById(id);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<CustomerPaymentDTO> getAll() {

        return customerPaymentService.getAll();
    }

    // ================= SEARCH BY BILL ID =================
    @GetMapping("/search/bill/{billId}")
    public List<CustomerPaymentDTO> searchByBill(@PathVariable Integer billId) {

        return customerPaymentService.searchByBill(billId);
    }

    // ================= SEARCH BY PAYMENT DATE =================
    @GetMapping("/search/date/{paymentDate}")
    public List<CustomerPaymentDTO> searchByDate(@PathVariable LocalDate paymentDate) {

        return customerPaymentService.searchByDate(paymentDate);
    }

}