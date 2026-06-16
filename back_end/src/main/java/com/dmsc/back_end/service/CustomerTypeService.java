package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerTypeDTO;


public interface CustomerTypeService {
    
    List<CustomerTypeDTO> getAllCustomerTypes();
    
    CustomerTypeDTO getCustomerTypeById(int id);
    
    CustomerTypeDTO saveCustomerType(CustomerTypeDTO dto);
    
    CustomerTypeDTO updateCustomerType(int id, CustomerTypeDTO dto);
    
    void deleteCustomerType(int id);
}
