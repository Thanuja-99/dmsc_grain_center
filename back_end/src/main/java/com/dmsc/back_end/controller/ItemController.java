package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.ItemDTO;
import com.dmsc.back_end.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // ================= GET ALL =================
    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable Integer id) {
        return itemService.getItemById(id);
    }

    // ================= SEARCH BY NAME =================
    @GetMapping("/search")
    public List<ItemDTO> searchItems(@RequestParam String name) {
        return itemService.searchItems(name);
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO saved = itemService.createItem(itemDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public ItemDTO updateItem(@PathVariable Integer id, @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(id, itemDTO);
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }
}
