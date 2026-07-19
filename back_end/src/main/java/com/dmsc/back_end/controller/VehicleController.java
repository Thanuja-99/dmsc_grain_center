package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.VehicleDTO;
import com.dmsc.back_end.service.VehicleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // ================= GET ALL =================
    @GetMapping
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public VehicleDTO getVehicleById(@PathVariable Integer id) {
        return vehicleService.getVehicleById(id);
    }

    // ================= SEARCH =================
    @GetMapping("/search")
    public List<VehicleDTO> searchVehicle(@RequestParam String plate) {
        return vehicleService.searchVehicle(plate);
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO saved = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public VehicleDTO updateVehicle(@PathVariable Integer id, @RequestBody VehicleDTO vehicleDTO) {
        return vehicleService.updateVehicle(id, vehicleDTO);
    }

    // ================= DELETE (SOFT) =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Integer id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok().build();
    }
}