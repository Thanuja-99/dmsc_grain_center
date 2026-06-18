package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.ItemDTO;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.ItemBrand;
import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.repository.ItemBrandRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.service.ItemService;

import jakarta.transaction.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemBrandRepository itemBrandRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // ================= GET BY ID =================
    @Override
    public ItemDTO getItemById(int id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return toDTO(item);
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= SEARCH BY ID =================
    @Override
    public List<ItemDTO> searchItems(int id) {
        return itemRepository.findById(id)
                .map(item -> List.of(toDTO(item)))
                .orElse(new ArrayList<>());
    }

    // ================= SEARCH BY NAME =================
    @Override
    public List<ItemDTO> searchItems(String name) {
        return itemRepository.findByItemNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public ItemDTO createItem(ItemDTO dto) {

        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Item item = new Item();

        item.setItemName(dto.getItemName());
        item.setItemVolume(dto.getItemVolume());
        item.setPurchasePrice(dto.getPurchasePrice());
        item.setSalesPrice(dto.getSalesPrice());
        item.setQuantity(dto.getQuantity());
        item.setManufacturingDate(dto.getManufacturingDate());
        item.setExpireDate(dto.getExpireDate());
        item.setItemNote(dto.getItemNote());

        item.setActive(true);
        item.setEnteredBy(dto.getEnteredBy());
        item.setEnteredDate(LocalDate.now());
        item.setUpdateBy(dto.getUpdateBy());
        item.setUpdateDate(LocalDate.now());

        item.setSupplier(supplier);

        // MANY TO MANY (ItemBrand)
        if (dto.getItemBrandIds() != null) {
            List<ItemBrand> brands =
                    itemBrandRepository.findAllById(dto.getItemBrandIds());
            item.setItemBrands(brands);
        }

        Item saved = itemRepository.save(item);
        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public ItemDTO updateItem(int id, ItemDTO dto) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        if (dto.getSupplierId() != null) {
            Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            item.setSupplier(supplier);
        }

        item.setItemName(dto.getItemName());
        item.setItemVolume(dto.getItemVolume());
        item.setPurchasePrice(dto.getPurchasePrice());
        item.setSalesPrice(dto.getSalesPrice());
        item.setQuantity(dto.getQuantity());
        item.setManufacturingDate(dto.getManufacturingDate());
        item.setExpireDate(dto.getExpireDate());
        item.setItemNote(dto.getItemNote());
        item.setActive(true);
        item.setUpdateBy(dto.getUpdateBy());
        item.setUpdateDate(LocalDate.now());

        // MANY TO MANY UPDATE
        if (dto.getItemBrandIds() != null) {
            List<ItemBrand> brands =
                    itemBrandRepository.findAllById(dto.getItemBrandIds());
            item.setItemBrands(brands);
        }

        Item updated = itemRepository.save(item);
        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void deleteItem(int id) {

        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setActive(false);
        itemRepository.save(item);
    }

    // ================= ENTITY → DTO =================
    private ItemDTO toDTO(Item item) {

        ItemDTO dto = new ItemDTO();

        dto.setItemId(item.getItemId());
        dto.setItemName(item.getItemName());
        dto.setItemVolume(item.getItemVolume());
        dto.setPurchasePrice(item.getPurchasePrice());
        dto.setSalesPrice(item.getSalesPrice());
        dto.setQuantity(item.getQuantity());
        dto.setManufacturingDate(item.getManufacturingDate());
        dto.setExpireDate(item.getExpireDate());
        dto.setItemNote(item.getItemNote());
        dto.setActive(item.isActive());
        dto.setEnteredBy(item.getEnteredBy());
        dto.setEnteredDate(item.getEnteredDate());
        dto.setUpdateBy(item.getUpdateBy());
        dto.setUpdateDate(item.getUpdateDate());

        // Supplier mapping
        if (item.getSupplier() != null) {
            dto.setSupplierId(item.getSupplier().getSupplierId());
            dto.setSupplierName(item.getSupplier().getSupplierName());
        }

        // ItemBrand mapping
        if (item.getItemBrands() != null) {
            List<Integer> brandIds = item.getItemBrands()
                    .stream()
                    .map(ItemBrand::getItemBrandId)
                    .collect(Collectors.toList());

            dto.setItemBrandNames(
        item.getItemBrands()
            .stream()
            .map(ItemBrand::getItemBrandName)
            .collect(Collectors.toList())
    );

            dto.setItemBrandIds(brandIds);
        }

        return dto;
    }
}