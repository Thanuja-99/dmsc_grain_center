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

import com.dmsc.back_end.dto.ItemBrandDTO;
import com.dmsc.back_end.service.ItemBrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item-brands")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ItemBrandController {

    @Autowired
    private ItemBrandService itemBrandService;

    // ================= GET ALL =================
    @GetMapping
    public List<ItemBrandDTO> getAllItemBrands() {
        return itemBrandService.getAllItemBrands();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ItemBrandDTO getItemBrandById(@PathVariable Integer id) {
        return itemBrandService.getItemBrandById(id);
    }

    // ================= SEARCH BY NAME =================
    @GetMapping("/search")
    public List<ItemBrandDTO> searchItemBrands(@RequestParam String name) {
        return itemBrandService.searchItemBrands(name);
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<ItemBrandDTO> createItemBrand(@RequestBody ItemBrandDTO dto) {

        ItemBrandDTO saved = itemBrandService.createItemBrand(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ItemBrandDTO updateItemBrand(
            @PathVariable Integer id,
            @RequestBody ItemBrandDTO dto) {

        return itemBrandService.updateItemBrand(id, dto);
    }

    // ================= DELETE (SOFT DELETE) =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItemBrand(@PathVariable Integer id) {

        itemBrandService.deleteItemBrand(id);
        return ResponseEntity.ok().build();
    }
}