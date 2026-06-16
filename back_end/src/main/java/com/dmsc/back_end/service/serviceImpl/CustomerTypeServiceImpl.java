package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerTypeDTO;
import com.dmsc.back_end.entity.CustomerType;
import com.dmsc.back_end.repository.CustomerTypeRepository;
import com.dmsc.back_end.service.CustomerTypeService;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService{
    @Autowired
    CustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerTypeDTO> getAllCustomerTypes() {
        return customerTypeRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerTypeDTO getCustomerTypeById(int id) {
        CustomerType ct = customerTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Type not found"));
        return toDTO(ct);
    }

    @Override
    public CustomerTypeDTO saveCustomerType(CustomerTypeDTO dto) {
        CustomerType ct = new CustomerType();
        ct.setCustomerTypeName(dto.getCustomerTypeName());
        ct.setActive(true);
        ct.setEnteredBy(dto.getEnteredBy());
        ct.setEnteredDate(LocalDate.now());
        CustomerType saved = customerTypeRepository.save(ct);
        return toDTO(saved);
    }

    @Override
    public CustomerTypeDTO updateCustomerType(int id, CustomerTypeDTO dto) {
        CustomerType ct = customerTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Type not found"));
        ct.setCustomerTypeName(dto.getCustomerTypeName());
        ct.setActive(dto.getIsActive());
        ct.setUpdateBy(dto.getUpdateBy());
        ct.setUpdateDate(LocalDate.now());
        CustomerType updated = customerTypeRepository.save(ct);
        return toDTO(updated);
    }

    @Override
    public void deleteCustomerType(int id) {
        customerTypeRepository.deleteById(id);
    }

    private CustomerTypeDTO toDTO(CustomerType ct) {
        CustomerTypeDTO dto = new CustomerTypeDTO();
        dto.setCustomerTypeId(ct.getCustomerTypeId());
        dto.setCustomerTypeName(ct.getCustomerTypeName());
        dto.setIsActive(ct.getIsActive());
        dto.setEnteredBy(ct.getEnteredBy());
        dto.setEnteredDate(ct.getEnteredDate());
        dto.setUpdateBy(ct.getUpdateBy());
        dto.setUpdateDate(ct.getUpdateDate());
        return dto;
    }
}
