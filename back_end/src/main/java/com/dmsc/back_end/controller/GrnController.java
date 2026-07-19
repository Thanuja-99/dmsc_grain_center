package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.GrnDTO;
import com.dmsc.back_end.service.GrnService;

@RestController
@RequestMapping("/api/grns")
@CrossOrigin(origins = "*")
public class GrnController {

    @Autowired
    private GrnService grnService;

    // =========================CREATE GRN=========================
     
    @PostMapping
    public ResponseEntity<GrnDTO> createGrn(@RequestBody GrnDTO grnDTO) {

        GrnDTO savedGrn = grnService.createGrn(grnDTO);

        return new ResponseEntity<>(savedGrn, HttpStatus.CREATED);
    }

    // =========================GET ALL GRNS=========================
     
    @GetMapping
    public ResponseEntity<List<GrnDTO>> getAllGrns() {

        return ResponseEntity.ok(grnService.getAllGrns());
    }

    // =========================GET GRN BY ID=========================
     
    @GetMapping("/{id}")
    public ResponseEntity<GrnDTO> getGrnById(@PathVariable int id) {

        return ResponseEntity.ok(grnService.getGrnById(id));
    }

    // =========================SEARCH BY ID=========================
    
    @GetMapping("/search")
    public ResponseEntity<List<GrnDTO>> searchGrnById(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String note) {

        if (id != null) {
            return ResponseEntity.ok(grnService.searchGrns(id));
        }

        if (note != null) {
            return ResponseEntity.ok(grnService.searchGrns(note));
        }

        return ResponseEntity.badRequest().build();
    }

    // =============UPDATE (Business Rule : Update is not allowed)======

    @PutMapping("/{id}")
    public ResponseEntity<GrnDTO> updateGrn(@PathVariable int id,@RequestBody GrnDTO grnDTO) {

        return ResponseEntity.ok(grnService.updateGrn(id, grnDTO));
    }

    // =========================SOFT DELETE=========================

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrn(@PathVariable int id) {

        grnService.deleteGrn(id);

        return ResponseEntity.ok("GRN deleted successfully.");
    }

}