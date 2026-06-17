package com.dmsc.back_end.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.SupplierTypeDTO;
import com.dmsc.back_end.entity.SupplierType;
import com.dmsc.back_end.repository.SupplierTypeRepository;
import com.dmsc.back_end.service.SupplierTypeService;

@Service
public class SupplierTypeServiceImpl implements SupplierTypeService {
    

    @Autowired
    SupplierTypeRepository supplierTypeRepository;

    @Override
    public List<SupplierTypeDTO> getAllSupplierTypes() {
        return supplierTypeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SupplierTypeDTO getSupplierTypeById(int id) {
        SupplierType st = supplierTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Type not found"));
        return toDTO(st);
    }

    @Override
    public SupplierTypeDTO saveSupplierType(SupplierTypeDTO supplierTypeDTO) {
        SupplierType st = new SupplierType();
        st.setSupplierTypeName(supplierTypeDTO.getSupplierTypeName());
        SupplierType saved = supplierTypeRepository.save(st);
        return toDTO(saved);
    }

    @Override
    public SupplierTypeDTO updateSupplierType(int id, SupplierTypeDTO supplierTypeDTO) {
        SupplierType st = supplierTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier Type not found"));
        st.setSupplierTypeName(supplierTypeDTO.getSupplierTypeName());
        SupplierType updated = supplierTypeRepository.save(st);
        return toDTO(updated);
    }

    @Override
    public void deleteSupplierType(int id) {
        supplierTypeRepository.deleteById(id);
    }

    private SupplierTypeDTO toDTO(SupplierType st) {
        SupplierTypeDTO supplierTypeDTO = new SupplierTypeDTO();
        supplierTypeDTO.setSupplierTypeId(st.getSupplierTypeId());
        supplierTypeDTO.setSupplierTypeName(st.getSupplierTypeName());

        return supplierTypeDTO;
    }
}
