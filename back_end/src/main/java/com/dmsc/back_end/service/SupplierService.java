package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.SupplierDTO;

public interface SupplierService {


    SupplierDTO createSupplier(SupplierDTO supplierDTO);
    List<SupplierDTO> getAllSuppliers();
    SupplierDTO getSupplierById(int id);
    List<SupplierDTO> searchSuppliers(int id);
    List<SupplierDTO> searchSuppliers(String name);
    SupplierDTO updateSupplier(int id, SupplierDTO supplierDTO);
    void deleteSupplier(int id);
}