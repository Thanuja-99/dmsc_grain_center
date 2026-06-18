package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerOrderItemDTO;

public interface CustomerOrderItemService {

    CustomerOrderItemDTO create(CustomerOrderItemDTO customerOrderItemDTO);
    List<CustomerOrderItemDTO> getAll();
    CustomerOrderItemDTO getById(int id);
    CustomerOrderItemDTO update(int id, CustomerOrderItemDTO customerOrderItemDTO);
    void delete(int id);
}
