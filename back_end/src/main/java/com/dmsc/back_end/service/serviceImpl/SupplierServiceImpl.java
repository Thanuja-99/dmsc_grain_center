package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.SupplierDTO;
import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.entity.Supplier;
import com.dmsc.back_end.entity.SupplierPhoneNumber;
import com.dmsc.back_end.entity.SupplierType;
import com.dmsc.back_end.repository.GenderRepository;
import com.dmsc.back_end.repository.SupplierPhoneNumberRepository;
import com.dmsc.back_end.repository.SupplierRepository;
import com.dmsc.back_end.repository.SupplierTypeRepository;
import com.dmsc.back_end.service.SupplierService;

import jakarta.transaction.Transactional;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierTypeRepository supplierTypeRepository;

    @Autowired
    SupplierPhoneNumberRepository supplierPhoneNumberRepository;

    @Autowired
    GenderRepository genderRepository;

    // ================= GET BY ID =================
    @Override
    public SupplierDTO getSupplierById(int id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        return toDTO(supplier);
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() {
    return supplierRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}

 // ================= SEARCH (simple by ID or replace later) =================
    @Override
    public List<SupplierDTO> searchSuppliers(int id) {
        return supplierRepository.findById(id)
                .map(supplier -> List.of(toDTO(supplier)))
                .orElse(new ArrayList<>());
    }
    
    @Override
    public List<SupplierDTO> searchSuppliers(String name) {
        return supplierRepository.findBySupplierNameContainingIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // ================== CREATE ========================
    @Override
    @Transactional
    public SupplierDTO createSupplier(SupplierDTO supplierDTO){

        SupplierType supplierType = supplierTypeRepository.findById(supplierDTO.getSupplierTypeId())
                .orElseThrow(() -> new RuntimeException("Supplier Type not found"));

        Gender gender = genderRepository.findById(supplierDTO.getGenderId())
                .orElseThrow(() -> new RuntimeException("Gender not found"));

                Supplier supplier = new Supplier();
                supplier.setSupplierName(supplierDTO.getSupplierName());
                supplier.setSupplierAddress(supplierDTO.getSupplierAddress());
                supplier.setSupplierNote(supplierDTO.getSupplierNote());
                supplier.setActive(true);
                supplier.setEnteredBy(supplierDTO.getEnteredBy());
                supplier.setEnteredDate(LocalDate.now());
                supplier.setSupplierType(supplierType);
                supplier.setGender(gender);
                supplier.setPhoneNumbers(new ArrayList<>());

                Supplier savedSupplier = supplierRepository.save(supplier);

                // save phone numbers
        if (supplierDTO.getPhoneNumbers() != null) {
            for (String phoneNum : supplierDTO.getPhoneNumbers()) {
                SupplierPhoneNumber phone = new SupplierPhoneNumber();
                phone.setSupplierPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setSupplier(savedSupplier);
                supplierPhoneNumberRepository.save(phone);
            }
        }
         return toDTO(savedSupplier);

    }


    // ================= UPDATE =================
    @Override
    @Transactional
    public SupplierDTO updateSupplier(int id, SupplierDTO supplierDTO) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        if (supplierDTO.getSupplierTypeId() != null) {
            SupplierType supplierType = supplierTypeRepository.findById(supplierDTO.getSupplierTypeId())
                    .orElseThrow(() -> new RuntimeException("Supplier Type not found"));
            supplier.setSupplierType(supplierType);
        }

        if (supplierDTO.getGenderId() != null) {
            Gender gender = genderRepository.findById(supplierDTO.getGenderId())
                    .orElseThrow(() -> new RuntimeException("Gender not found"));
            supplier.setGender(gender);
        }

        supplier.setSupplierName(supplierDTO.getSupplierName());
        supplier.setSupplierAddress(supplierDTO.getSupplierAddress());
        supplier.setSupplierNote(supplierDTO.getSupplierNote());

        if (supplierDTO.getIsActive() != null) {
            supplier.setActive(supplierDTO.getIsActive());
        }

        supplier.setUpdateBy(supplierDTO.getUpdateBy());
        supplier.setUpdateDate(LocalDate.now());

        // remove old phones (simple way)
        supplierPhoneNumberRepository.deleteBySupplier_SupplierId(id);

        // add new phones
        if (supplierDTO.getPhoneNumbers() != null) {
            for (String phoneNum : supplierDTO.getPhoneNumbers()) {
                SupplierPhoneNumber phone = new SupplierPhoneNumber();
                phone.setSupplierPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setSupplier(supplier);
                supplierPhoneNumberRepository.save(phone);
            }
        }

        Supplier updated = supplierRepository.save(supplier);
        return toDTO(updated);
    }

// ================= DELETE (soft delete) =================
    @Override
    public void deleteSupplier(int id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        supplier.setActive(false);
        supplierRepository.save(supplier);
    }


      // ================= ENTITY → DTO =================
    private SupplierDTO toDTO(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setSupplierId(supplier.getSupplierId());
        supplierDTO.setSupplierName(supplier.getSupplierName());
        supplierDTO.setSupplierAddress(supplier.getSupplierAddress());
        supplierDTO.setSupplierNote(supplier.getSupplierNote());
        supplierDTO.setIsActive(supplier.isActive());
        supplierDTO.setEnteredBy(supplier.getEnteredBy());
        supplierDTO.setEnteredDate(supplier.getEnteredDate());
        supplierDTO.setUpdateBy(supplier.getUpdateBy());
        supplierDTO.setUpdateDate(supplier.getUpdateDate());

        if (supplier.getSupplierType() != null) {
            supplierDTO.setSupplierTypeId(supplier.getSupplierType().getSupplierTypeId());
            supplierDTO.setSupplierTypeName(supplier.getSupplierType().getSupplierTypeName());
        }

        if (supplier.getGender() != null) {
            supplierDTO.setGenderId(supplier.getGender().getGenderId());
            supplierDTO.setGenderName(supplier.getGender().getGenderName());
        }

        if (supplier.getPhoneNumbers() != null) {
            supplierDTO.setPhoneNumbers(
                    supplier.getPhoneNumbers()
                            .stream()
                            .map(SupplierPhoneNumber::getSupplierPhoneNumber)
                            .collect(Collectors.toList())
            );
        }

        return supplierDTO;
    }

}
