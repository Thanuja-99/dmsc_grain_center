package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.SupplierPhoneNumberDTO;

public interface SupplierPhoneNumberService {
    
     List<SupplierPhoneNumberDTO> getAllPhoneNumbers();

    List<SupplierPhoneNumberDTO> getPhoneNumbersBySupplierId(int supplierId);

    SupplierPhoneNumberDTO createPhoneNumber(SupplierPhoneNumberDTO supplierPhoneNumberDTO);

    SupplierPhoneNumberDTO updatePhoneNumber(int id, SupplierPhoneNumberDTO supplierPhoneNumberDTO);

    void deletePhoneNumber(int id);
}
