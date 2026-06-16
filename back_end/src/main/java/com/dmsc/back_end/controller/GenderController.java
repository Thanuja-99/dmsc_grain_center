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

import com.dmsc.back_end.dto.GenderDTO;
import com.dmsc.back_end.service.GenderService;

@RestController
@RequestMapping("/api/genders")
@CrossOrigin(origins = "*")
public class GenderController {

    @Autowired
    GenderService genderService;

    // Get all genders
    @GetMapping
    public List<GenderDTO> getAllGenders() {
        return genderService.getAllGenders();
    }

    // Get gender by ID
    @GetMapping("/{id}")
    public GenderDTO getGenderById(@PathVariable Integer id) {
        return genderService.getGenderById(id);
    }

    // Create gender
    @PostMapping
    public ResponseEntity<GenderDTO> createGender(@RequestBody GenderDTO genderDTO) {
        GenderDTO saved = genderService.createGender(genderDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Update gender
    @PutMapping("/{id}")
    public GenderDTO updateGender(@PathVariable Integer id, @RequestBody GenderDTO genderDTO) {
        return genderService.updateGender(id, genderDTO);
    }

    // Delete gender
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGender(@PathVariable Integer id) {
        genderService.deleteGender(id);
        return ResponseEntity.ok().build();
    }
}

