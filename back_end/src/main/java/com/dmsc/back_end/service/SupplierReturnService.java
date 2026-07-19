package com.dmsc.back_end.service;

import java.time.LocalDate;
import java.util.List;

import com.dmsc.back_end.dto.SupplierReturnDTO;

public interface SupplierReturnService {

    SupplierReturnDTO create(SupplierReturnDTO supplierReturnDTO);

    SupplierReturnDTO update(int id, SupplierReturnDTO supplierReturnDTO);

    void delete(int id);

    SupplierReturnDTO getById(int id);

    List<SupplierReturnDTO> getAll();

    List<SupplierReturnDTO> searchByGrnId(int grnId);

    List<SupplierReturnDTO> searchByItemName(String itemName);

    List<SupplierReturnDTO> searchByReturnDate(LocalDate returnDate);

}