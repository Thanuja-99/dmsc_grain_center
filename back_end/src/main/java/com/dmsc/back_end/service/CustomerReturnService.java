package com.dmsc.back_end.service;

import java.time.LocalDate;
import java.util.List;

import com.dmsc.back_end.dto.CustomerReturnDTO;

public interface CustomerReturnService {

    CustomerReturnDTO create(CustomerReturnDTO customerReturnDTO);

    CustomerReturnDTO update(int id, CustomerReturnDTO customerReturnDTO);

    void delete(int id);

    CustomerReturnDTO getById(int id);

    List<CustomerReturnDTO> getAll();

    List<CustomerReturnDTO> searchByOrderId(int customerOrderId);

    List<CustomerReturnDTO> searchByItemName(String itemName);

    List<CustomerReturnDTO> searchByReturnDate(LocalDate returnDate);

}