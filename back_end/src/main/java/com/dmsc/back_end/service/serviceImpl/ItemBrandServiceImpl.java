package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.ItemBrandDTO;
import com.dmsc.back_end.entity.Item;
import com.dmsc.back_end.entity.ItemBrand;
import com.dmsc.back_end.repository.ItemBrandRepository;
import com.dmsc.back_end.repository.ItemRepository;
import com.dmsc.back_end.service.ItemBrandService;

import jakarta.transaction.Transactional;

@Service
public class ItemBrandServiceImpl implements ItemBrandService {

    @Autowired
    private ItemBrandRepository itemBrandRepository;

    @Autowired
    private ItemRepository itemRepository;

    // ================= GET BY ID =================
    @Override
    public ItemBrandDTO getItemBrandById(int id) {

        ItemBrand brand = itemBrandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Brand not found"));

        return toDTO(brand);
    }

    // ================= GET ALL =================
    @Override
    public List<ItemBrandDTO> getAllItemBrands() {

        return itemBrandRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= SEARCH BY ID =================
    @Override
    public List<ItemBrandDTO> searchItemBrands(int id) {

        return itemBrandRepository.findById(id)
                .map(brand -> List.of(toDTO(brand)))
                .orElse(new ArrayList<>());
    }

    // ================= SEARCH BY NAME =================
    @Override
    public List<ItemBrandDTO> searchItemBrands(String name) {

        return itemBrandRepository
                .findByItemBrandNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public ItemBrandDTO createItemBrand(ItemBrandDTO dto) {

        ItemBrand brand = new ItemBrand();

        brand.setItemBrandName(dto.getItemBrandName());
        brand.setActive(true);
        brand.setEnteredBy(dto.getEnteredBy());
        brand.setEnteredDate(LocalDate.now());
        brand.setUpdateBy(dto.getUpdateBy());
        brand.setUpdateDate(LocalDate.now());

        // save brand first
        ItemBrand saved = itemBrandRepository.save(brand);

        // Many-to-Many (optional attach items)
        if (dto.getItemIds() != null) {
            List<Item> items = itemRepository.findAllById(dto.getItemIds());

            for (Item item : items) {
                item.getItemBrands().add(saved);
            }
        }

        return toDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public ItemBrandDTO updateItemBrand(int id, ItemBrandDTO dto) {

        ItemBrand brand = itemBrandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Brand not found"));

        brand.setItemBrandName(dto.getItemBrandName());
        brand.setActive(true);
        brand.setUpdateBy(dto.getUpdateBy());
        brand.setUpdateDate(LocalDate.now());

        // OPTIONAL: update many-to-many
        if (dto.getItemIds() != null) {

            List<Item> items = itemRepository.findAllById(dto.getItemIds());

            // clear old relations
            for (Item item : brand.getItems()) {
                item.getItemBrands().remove(brand);
            }

            brand.getItems().clear();

            // add new relations
            for (Item item : items) {
                item.getItemBrands().add(brand);
                brand.getItems().add(item);
            }
        }

        ItemBrand updated = itemBrandRepository.save(brand);
        return toDTO(updated);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void deleteItemBrand(int id) {

        ItemBrand brand = itemBrandRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item Brand not found"));

        brand.setActive(false);
        itemBrandRepository.save(brand);
    }

    // ================= ENTITY → DTO =================
    private ItemBrandDTO toDTO(ItemBrand brand) {

        ItemBrandDTO dto = new ItemBrandDTO();

        dto.setItemBrandId(brand.getItemBrandId());
        dto.setItemBrandName(brand.getItemBrandName());
        dto.setActive(brand.isActive());
        dto.setEnteredBy(brand.getEnteredBy());
        dto.setEnteredDate(brand.getEnteredDate());
        dto.setUpdateBy(brand.getUpdateBy());
        dto.setUpdateDate(brand.getUpdateDate());

        // Many-to-Many mapping
        if (brand.getItems() != null) {

            dto.setItemIds(
                    brand.getItems()
                            .stream()
                            .map(Item::getItemId)
                            .collect(Collectors.toList())
            );

            dto.setItemNames(
                    brand.getItems()
                            .stream()
                            .map(Item::getItemName)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}