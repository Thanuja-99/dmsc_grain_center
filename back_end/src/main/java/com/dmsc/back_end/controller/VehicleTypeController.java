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

import com.dmsc.back_end.dto.VehicleTypeDTO;
import com.dmsc.back_end.service.VehicleTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicle-types")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleTypeController {

    @Autowired
    private VehicleTypeService vehicleTypeService;
    

    @GetMapping
    public List<VehicleTypeDTO> getAllTypes() {
        return vehicleTypeService.getAllTypes();
    }

    @GetMapping("/{id}")
    public VehicleTypeDTO getTypeById(@PathVariable Integer id) {
        return vehicleTypeService.getTypeById(id);
    }

    @GetMapping("/search")
    public List<VehicleTypeDTO> searchType(@RequestParam String name) {
        return vehicleTypeService.searchType(name);
    }

    @PostMapping
    public ResponseEntity<VehicleTypeDTO> createType(@RequestBody VehicleTypeDTO vehicleTypeDTO) {
        VehicleTypeDTO saved = vehicleTypeService.createType(vehicleTypeDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public VehicleTypeDTO updateType(@PathVariable Integer id, @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return vehicleTypeService.updateType(id, vehicleTypeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
        vehicleTypeService.deleteType(id);
        return ResponseEntity.ok().build();
    }
}
