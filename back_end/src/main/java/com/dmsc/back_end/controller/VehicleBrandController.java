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

import com.dmsc.back_end.dto.VehicleBrandDTO;
import com.dmsc.back_end.service.VehicleBrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vehicle-brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VehicleBrandController {

    @Autowired
    VehicleBrandService vehicleBrandService;

    @GetMapping
    public List<VehicleBrandDTO> getAllBrands() {
        return vehicleBrandService.getAllBrands();
    }

    @GetMapping("/{id}")
    public VehicleBrandDTO getBrandById(@PathVariable Integer id) {
        return vehicleBrandService.getBrandById(id);
    }

    @GetMapping("/search")
    public List<VehicleBrandDTO> searchBrand(@RequestParam String name) {
        return vehicleBrandService.searchBrand(name);
    }

    @PostMapping
    public ResponseEntity<VehicleBrandDTO> createBrand(@RequestBody VehicleBrandDTO vehicleBrandDTO) {
        VehicleBrandDTO saved = vehicleBrandService.createBrand(vehicleBrandDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public VehicleBrandDTO updateBrand(@PathVariable Integer id, @RequestBody VehicleBrandDTO vehicleBrandDTO) {
        return vehicleBrandService.updateBrand(id, vehicleBrandDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        vehicleBrandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }
}
