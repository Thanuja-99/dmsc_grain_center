package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.BillStatusDTO;
import com.dmsc.back_end.service.BillStatusService;

@RestController
@RequestMapping("/api/bill-status")
@CrossOrigin(origins = "*")
public class BillStatusController {

    @Autowired
    private BillStatusService billStatusService;

    // ================= CREATE =================
    @PostMapping
    public BillStatusDTO createBillStatus(@RequestBody BillStatusDTO billStatusDTO) {

        return billStatusService.createBillStatus(billStatusDTO);
    }

    // ================= GET ALL =================
    @GetMapping
    public List<BillStatusDTO> getAllBillStatuses() {

        return billStatusService.getAllBillStatuses();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public BillStatusDTO getBillStatusById(@PathVariable int id) {

        return billStatusService.getBillStatusById(id);
    }

    // ================= SEARCH BY ID =================
    @GetMapping("/search/id/{id}")
    public List<BillStatusDTO> searchBillStatusById(@PathVariable int id) {

        return billStatusService.searchBillStatuses(id);
    }

    // ================= SEARCH BY NAME =================
    @GetMapping("/search/name/{name}")
    public List<BillStatusDTO> searchBillStatusByName(@PathVariable String name) {

        return billStatusService.searchBillStatuses(name);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public BillStatusDTO updateBillStatus(
            @PathVariable int id,
            @RequestBody BillStatusDTO billStatusDTO) {

        return billStatusService.updateBillStatus(id, billStatusDTO);
    }

    // ================= SOFT DELETE =================
    @DeleteMapping("/{id}")
    public String deleteBillStatus(@PathVariable int id) {

        billStatusService.deleteBillStatus(id);

        return "Bill Status deleted successfully.";
    }

}