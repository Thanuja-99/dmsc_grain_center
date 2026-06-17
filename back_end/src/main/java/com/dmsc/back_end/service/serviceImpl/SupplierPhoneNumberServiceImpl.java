package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.dto.SupplierPhoneNumberDTO;
import com.dmsc.back_end.entity.SupplierPhoneNumber;
import com.dmsc.back_end.repository.SupplierPhoneNumberRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.service.SupplierPhoneNumberService;


@Service
public class SupplierPhoneNumberServiceImpl implements SupplierPhoneNumberService {
    

    @Autowired
    SupplierPhoneNumberRepository supplierPhoneNumberRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public List<SupplierPhoneNumberDTO> getAllPhoneNumbers() {
        return supplierPhoneNumberRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<SupplierPhoneNumberDTO> getPhoneNumbersBySupplierId(int supplierId) {
        return supplierPhoneNumberRepository.findBySupplier_SupplierId(supplierId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierPhoneNumberDTO createPhoneNumber(SupplierPhoneNumberDTO supplierPhoneNumberDTO) {
        Supplier supplier = supplierRepository.findById(supplierPhoneNumberDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        SupplierPhoneNumber phone = new SupplierPhoneNumber();
        phone.setSupplier(supplier);
        phone.setSupplierPhoneNumber(supplierPhoneNumberDTO.getSupplierPhoneNumber());
        phone.setActive(true);
        phone.setEnteredDate(LocalDate.now());
        phone.setSupplier(supplier);
        
        SupplierPhoneNumber saved = supplierPhoneNumberRepository.save(phone);
        return toDTO(saved);
    }

    @Override
    public SupplierPhoneNumberDTO updatePhoneNumber(int id, SupplierPhoneNumberDTO supplierPhoneNumberDTO) {
        SupplierPhoneNumber phone = supplierPhoneNumberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone number not found"));
        phone.setSupplierPhoneNumber(supplierPhoneNumberDTO.getSupplierPhoneNumber());
        phone.setActive(supplierPhoneNumberDTO.getIsActive());
        phone.setUpdateDate(LocalDate.now());
        SupplierPhoneNumber updated = supplierPhoneNumberRepository.save(phone);
        return toDTO(updated);
    }

    @Override
    public void deletePhoneNumber(int id) {
        supplierPhoneNumberRepository.deleteById(id);
    }

    private SupplierPhoneNumberDTO toDTO(SupplierPhoneNumber phone) {
        SupplierPhoneNumberDTO dto = new SupplierPhoneNumberDTO();
        dto.setSupplierPhoneNumberId(phone.getSupplierPhoneNumberId());
        dto.setSupplierPhoneNumber(phone.getSupplierPhoneNumber());
        dto.setIsActive(phone.isActive());
        dto.setSupplierId(phone.getSupplier() != null ? phone.getSupplier().getSupplierId() : null);
        return dto;
    }

    
}
