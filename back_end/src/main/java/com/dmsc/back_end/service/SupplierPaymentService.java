package com.dmsc.back_end.service;

import java.time.LocalDate;
import java.util.List;

import com.dmsc.back_end.dto.SupplierPaymentDTO;

public interface SupplierPaymentService {

    SupplierPaymentDTO create(SupplierPaymentDTO dto);

    SupplierPaymentDTO update(int id, SupplierPaymentDTO dto);

    void delete(int id);

    SupplierPaymentDTO getById(int id);

    List<SupplierPaymentDTO> getAll();

    List<SupplierPaymentDTO> searchByGrn(Integer grnId);

    List<SupplierPaymentDTO> searchByDate(LocalDate paymentDate);

}