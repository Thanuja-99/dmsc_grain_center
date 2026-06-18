package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerOrderDTO;

public interface CustomerOrderService {
    
    CustomerOrderDTO create(CustomerOrderDTO customerOrderDTO);
    List<CustomerOrderDTO> getAll();
    CustomerOrderDTO getById(int id);
    CustomerOrderDTO update(int id, CustomerOrderDTO customerOrderDTO);
    void delete(int id);
}
