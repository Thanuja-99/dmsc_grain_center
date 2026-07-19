package com.dmsc.back_end.service;

import java.time.LocalDate;
import java.util.List;

import com.dmsc.back_end.dto.CustomerPaymentDTO;

public interface CustomerPaymentService {

    CustomerPaymentDTO create(CustomerPaymentDTO dto);

    CustomerPaymentDTO update(int id, CustomerPaymentDTO dto);

    void delete(int id);

    CustomerPaymentDTO getById(int id);

    List<CustomerPaymentDTO> getAll();

    List<CustomerPaymentDTO> searchByBill(Integer billId);

    List<CustomerPaymentDTO> searchByDate(LocalDate paymentDate);

}