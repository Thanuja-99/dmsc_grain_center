package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.StoreDTO;
import com.dmsc.back_end.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // ================= GET ALL =================
    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAllStores();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    public StoreDTO getStoreById(@PathVariable Integer id) {
        return storeService.getStoreById(id);
    }

    // ================= SEARCH BY NAME =================
    @GetMapping("/search")
    public List<StoreDTO> searchStores(@RequestParam String name) {
        return storeService.searchStore(name);
    }

    // ================= CREATE =================
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO) {

        StoreDTO savedStore = storeService.createStore(storeDTO);

        return new ResponseEntity<>(
                savedStore,
                HttpStatus.CREATED
        );
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    public StoreDTO updateStore(
            @PathVariable Integer id,
            @RequestBody StoreDTO storeDTO) {

        return storeService.updateStore(id, storeDTO);
    }

    // ================= DELETE (SOFT DELETE) =================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id) {

        storeService.deleteStore(id);

        return ResponseEntity.ok().build();
    }
}