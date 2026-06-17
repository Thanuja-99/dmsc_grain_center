package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.SupplierTypeDTO;

public interface SupplierTypeService {

    List<SupplierTypeDTO> getAllSupplierTypes();
    
    SupplierTypeDTO getSupplierTypeById(int id);
    
    SupplierTypeDTO saveSupplierType(SupplierTypeDTO supplierTypeDTO);
    
    SupplierTypeDTO updateSupplierType(int id, SupplierTypeDTO supplierTypeDTO);
    
    void deleteSupplierType(int id);
}
