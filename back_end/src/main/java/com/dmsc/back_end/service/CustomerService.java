package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerDTO;


public interface CustomerService {
    
    CustomerDTO createCustomer(CustomerDTO dto);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO getCustomerById(int id);
    List<CustomerDTO> searchCustomers(int id);
    List<CustomerDTO> searchCustomers(String name);
    CustomerDTO updateCustomer(int id, CustomerDTO dto);
    void deleteCustomer(int id);
}
