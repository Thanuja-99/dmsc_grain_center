package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.entity.CustomerType;

public interface CustomerTypeService {
    CustomerType createCustomerType(CustomerType customerType);

    List<CustomerType> getAllCustomerTypes();

    CustomerType getCustomerTypeById(int id);

    CustomerType updateCustomerType(int id, CustomerType customerType);

    void deleteCustomerType(int id);
}
