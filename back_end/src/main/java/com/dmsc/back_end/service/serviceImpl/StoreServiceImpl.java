package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.StoreDTO;
import com.dmsc.back_end.entity.Store;
import com.dmsc.back_end.repository.StoreRepository;
import com.dmsc.back_end.service.StoreService;

import jakarta.transaction.Transactional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    // ================= CREATE =================
    @Override
    @Transactional
    public StoreDTO createStore(StoreDTO storeDTO) {

        Store store = new Store();

        store.setStoreName(storeDTO.getStoreName());
        store.setStoreAddress(storeDTO.getStoreAddress());
        store.setStoreNote(storeDTO.getStoreNote());

        store.setActive(true);
        store.setEnteredBy(storeDTO.getEnteredBy());
        store.setEnteredDate(LocalDate.now());

        return toDTO(storeRepository.save(store));
    }

    // ================= GET ALL =================
    @Override
    public List<StoreDTO> getAllStores() {
        return storeRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= GET BY ID =================
    @Override
    public StoreDTO getStoreById(int id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        return toDTO(store);
    }

    // ================= SEARCH =================
    @Override
    public List<StoreDTO> searchStore(String name) {

        return storeRepository.findByStoreNameContainingIgnoreCase(name)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public StoreDTO updateStore(int id, StoreDTO storeDTO) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setStoreName(storeDTO.getStoreName());
        store.setStoreAddress(storeDTO.getStoreAddress());
        store.setStoreNote(storeDTO.getStoreNote());

        store.setUpdateBy(storeDTO.getUpdateBy());
        store.setUpdateDate(LocalDate.now());

        return toDTO(storeRepository.save(store));
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void deleteStore(int id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        store.setActive(false);
        storeRepository.save(store);
    }

    // ================= ENTITY → DTO =================
    private StoreDTO toDTO(Store store) {

        StoreDTO dto = new StoreDTO();

        dto.setStoreId(store.getStoreId());
        dto.setStoreName(store.getStoreName());
        dto.setStoreAddress(store.getStoreAddress());
        dto.setStoreNote(store.getStoreNote());
        dto.setActive(store.isActive());

        dto.setEnteredBy(store.getEnteredBy());
        dto.setEnteredDate(store.getEnteredDate());

        dto.setUpdateBy(store.getUpdateBy());
        dto.setUpdateDate(store.getUpdateDate());

        return dto;
    }
}