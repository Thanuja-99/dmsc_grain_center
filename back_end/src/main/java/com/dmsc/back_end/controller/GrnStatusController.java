package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.GrnStatusDTO;
import com.dmsc.back_end.service.GrnStatusService;

@RestController
@RequestMapping("/api/grn-statuses")
@CrossOrigin(origins = "*")
public class GrnStatusController {

    private final GrnStatusService grnStatusService;

    public GrnStatusController(GrnStatusService grnStatusService) {
        this.grnStatusService = grnStatusService;
    }

    // ========================= CREATE GRN STATUS =========================
    @PostMapping
    public ResponseEntity<GrnStatusDTO> createGrnStatus(@RequestBody GrnStatusDTO grnStatusDTO) {

        GrnStatusDTO saved = grnStatusService.createGrnStatus(grnStatusDTO);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ========================= GET ALL GRN STATUSES =========================
    @GetMapping
    public ResponseEntity<List<GrnStatusDTO>> getAllGrnStatuses() {

        List<GrnStatusDTO> statuses = grnStatusService.getAllGrnStatuses();

        return ResponseEntity.ok(statuses);
    }

    // ========================= GET GRN STATUS BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<GrnStatusDTO> getGrnStatusById(@PathVariable int id) {

        GrnStatusDTO status = grnStatusService.getGrnStatusById(id);

        return ResponseEntity.ok(status);
    }

    // ========================= SEARCH =========================
    @GetMapping("/search")
    public ResponseEntity<List<GrnStatusDTO>> searchGrnStatuses(

            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name) {

        if (id != null) {
            return ResponseEntity.ok(grnStatusService.searchGrnStatuses(id));
        }

        if (name != null) {
            return ResponseEntity.ok(grnStatusService.searchGrnStatuses(name));
        }

        return ResponseEntity.badRequest().build();
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<GrnStatusDTO> updateGrnStatus(
            @PathVariable int id,
            @RequestBody GrnStatusDTO grnStatusDTO) {

        GrnStatusDTO updated = grnStatusService.updateGrnStatus(id, grnStatusDTO);

        return ResponseEntity.ok(updated);
    }

    // ========================= DELETE (SOFT DELETE) =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrnStatus(@PathVariable int id) {

        grnStatusService.deleteGrnStatus(id);

        return ResponseEntity.ok("GRN Status deleted successfully.");
    }

}