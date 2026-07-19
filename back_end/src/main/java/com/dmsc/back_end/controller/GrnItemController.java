package com.dmsc.back_end.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dmsc.back_end.dto.GrnItemDTO;
import com.dmsc.back_end.service.GrnItemService;

@RestController
@RequestMapping("/api/grn-items")
@CrossOrigin(origins = "*")
public class GrnItemController {

    private final GrnItemService grnItemService;

    public GrnItemController(GrnItemService grnItemService) {
        this.grnItemService = grnItemService;
    }

    // ========================= CREATE GRN ITEM =========================
    @PostMapping
    public ResponseEntity<GrnItemDTO> createGrnItem(@RequestBody GrnItemDTO dto) {

        GrnItemDTO saved = grnItemService.createGrnItem(dto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ========================= GET ALL GRN ITEMS =========================
    @GetMapping
    public ResponseEntity<List<GrnItemDTO>> getAllGrnItems() {

        List<GrnItemDTO> list = grnItemService.getAllGrnItems();

        return ResponseEntity.ok(list);
    }

    // ========================= GET GRN ITEM BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity<GrnItemDTO> getGrnItemById(@PathVariable int id) {

        GrnItemDTO dto = grnItemService.getGrnItemById(id);

        return ResponseEntity.ok(dto);
    }

    // ========================= SEARCH =========================
    @GetMapping("/search")
    public ResponseEntity<List<GrnItemDTO>> searchGrnItems(

            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String itemName) {

        if (id != null) {
            return ResponseEntity.ok(grnItemService.searchGrnItems(id));
        }

        if (itemName != null) {
            return ResponseEntity.ok(grnItemService.searchGrnItems(itemName));
        }

        return ResponseEntity.badRequest().build();
    }

    // ========================= UPDATE =========================
    @PutMapping("/{id}")
    public ResponseEntity<GrnItemDTO> updateGrnItem(
            @PathVariable int id,
            @RequestBody GrnItemDTO dto) {

        GrnItemDTO updated = grnItemService.updateGrnItem(id, dto);

        return ResponseEntity.ok(updated);
    }

    // ========================= DELETE (SOFT DELETE) =========================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGrnItem(@PathVariable int id) {

        grnItemService.deleteGrnItem(id);

        return ResponseEntity.ok("GRN Item deleted successfully.");
    }

}