package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerOrderStatusDTO;

public interface CustomerOrderStatusService {
    CustomerOrderStatusDTO create(CustomerOrderStatusDTO customerOrderStatusDTO);
    List<CustomerOrderStatusDTO> getAll();
    CustomerOrderStatusDTO getById(int id);
    CustomerOrderStatusDTO update(int id, CustomerOrderStatusDTO customerOrderStatusDTO);
    void delete(int id);
}
