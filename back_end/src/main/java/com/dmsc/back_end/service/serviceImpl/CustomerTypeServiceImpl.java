package com.dmsc.back_end.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.entity.CustomerType;
import com.dmsc.back_end.repository.CustomerTypeRepository;
import com.dmsc.back_end.service.CustomerTypeService;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService{
    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public CustomerType createCustomerType(CustomerType customerType) {
        return customerTypeRepository.save(customerType);
    }

    @Override
    public List<CustomerType> getAllCustomerTypes() {
        return customerTypeRepository.findAll();
    }

    @Override
    public CustomerType getCustomerTypeById(int id) {
        return customerTypeRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerType updateCustomerType(int id, CustomerType customerType) {

        CustomerType existingCustomerType = customerTypeRepository.findById(id).orElse(null);

        if (existingCustomerType != null) {
            existingCustomerType.setCustomerTypeName(customerType.getCustomerTypeName());
            existingCustomerType.setActive(customerType.isActive());
            existingCustomerType.setEnteredBy(customerType.getEnteredBy());
            existingCustomerType.setEnteredDate(customerType.getEnteredDate());
            existingCustomerType.setUpdateBy(customerType.getUpdateBy());
            existingCustomerType.setUpdateDate(customerType.getUpdateDate());
            return customerTypeRepository.save(existingCustomerType);
        }

        return null;
    }

    @Override
    public void deleteCustomerType(int id) {
        customerTypeRepository.deleteById(id);
    }
}
