package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.dto.VehicleModelDTO;
import com.dmsc.back_end.service.VehicleModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicle-models")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleModelController {

    @Autowired
    private VehicleModelService vehicleModelService;

    @GetMapping
    public List<VehicleModelDTO> getAllModels() {
        return vehicleModelService.getAllModels();
    }

    @GetMapping("/{id}")
    public VehicleModelDTO getModelById(@PathVariable Integer id) {
        return vehicleModelService.getModelById(id);
    }

    @GetMapping("/search")
    public List<VehicleModelDTO> searchModel(@RequestParam String name) {
        return vehicleModelService.searchModel(name);
    }

    @PostMapping
    public ResponseEntity<VehicleModelDTO> createModel(@RequestBody VehicleModelDTO dto) {
        VehicleModelDTO saved = vehicleModelService.createModel(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public VehicleModelDTO updateModel(@PathVariable Integer id, @RequestBody VehicleModelDTO dto) {
        return vehicleModelService.updateModel(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModel(@PathVariable Integer id) {
        vehicleModelService.deleteModel(id);
        return ResponseEntity.ok().build();
    }
}
