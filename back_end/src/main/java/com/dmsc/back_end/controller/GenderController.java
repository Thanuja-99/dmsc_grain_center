package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.service.GenderService;

@RestController
@RequestMapping("/api/genders")
@CrossOrigin
public class GenderController {

    @Autowired
    private GenderService genderService;

    
    @PostMapping
    public Gender createGender(@RequestBody Gender gender) {
        return genderService.createGender(gender);
    }

    @GetMapping
    public List<Gender> getAllGenders() {
        return genderService.getAllGenders();
    }
    
    @GetMapping("/{id}")
    public Gender getGenderById(@PathVariable int id) {
        return genderService.getGenderById(id);
    }

    @PutMapping("/{id}")
    public Gender updateGender(@PathVariable int id, Gender gender){
        return genderService.updateGender(id, gender);
    }

    
    @DeleteMapping("/{id}")
    public void deleteGender(@PathVariable int id){
        genderService.deleteGender(id);
    }
}    

