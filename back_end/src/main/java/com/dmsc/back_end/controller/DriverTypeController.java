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
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.dto.DriverTypeDTO;
import com.dmsc.back_end.service.DriverTypeService;


@RestController
@RequestMapping("/api/driver-types")
@CrossOrigin(origins = "*")
public class DriverTypeController{

    @Autowired
    DriverTypeService driverTypeService;

    // Get All driver types
    @GetMapping
    public List<DriverTypeDTO> getAllDriverTypes() {
        return driverTypeService.getAllDriverTypes();
    }

    // Get driver type by ID
    @GetMapping("/{id}")
    public DriverTypeDTO getDriverTypeById(@PathVariable Integer id) {
        return driverTypeService.getDriverTypeById(id);
    }

    // Create driver type
    @PostMapping
    public ResponseEntity<DriverTypeDTO> createDriverType(@RequestBody DriverTypeDTO driverTypeDTO) {
        DriverTypeDTO saved = driverTypeService.saveDriverType(driverTypeDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update driver type
    @PutMapping("/{id}")
    public DriverTypeDTO updateDriverType(@PathVariable Integer id, @RequestBody DriverTypeDTO driverTypeDTO) {
        return driverTypeService.updateDriverType(id, driverTypeDTO);
    }

    // Delete driver type
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriverType(@PathVariable Integer id) {
        driverTypeService.deleteDriverType(id);
        return ResponseEntity.ok().build();
}
}