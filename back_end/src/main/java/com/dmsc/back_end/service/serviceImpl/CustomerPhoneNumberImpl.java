package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerPhoneNumberDTO;
import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.entity.CustomerPhoneNumber;
import com.dmsc.back_end.repository.CustomerPhoneNumberRepository;
import com.dmsc.back_end.repository.CustomerRepository;
import com.dmsc.back_end.service.CustomerPhoneNumberService;

@Service
public class CustomerPhoneNumberImpl implements CustomerPhoneNumberService{
     @Autowired
    CustomerPhoneNumberRepository phoneRepository;
    
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<CustomerPhoneNumberDTO> getAllPhoneNumbers() {
        return phoneRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CustomerPhoneNumberDTO> getPhoneNumbersByCustomerId(int customerId) {
        return phoneRepository.findByCustomer_CustomerId(customerId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerPhoneNumberDTO createPhoneNumber(CustomerPhoneNumberDTO dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        CustomerPhoneNumber phone = new CustomerPhoneNumber();
        phone.setCustomerPhoneNumber(dto.getCustomerPhoneNumber());
        phone.setActive(true);
        phone.setEnteredDate(LocalDate.now());
        phone.setCustomer(customer);
        
        CustomerPhoneNumber saved = phoneRepository.save(phone);
        return toDTO(saved);
    }

    @Override
    public CustomerPhoneNumberDTO updatePhoneNumber(int id, CustomerPhoneNumberDTO dto) {
        CustomerPhoneNumber phone = phoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Phone number not found"));
        phone.setCustomerPhoneNumber(dto.getCustomerPhoneNumber());
        phone.setActive(dto.getIsActive());
        phone.setUpdateDate(LocalDate.now());
        CustomerPhoneNumber updated = phoneRepository.save(phone);
        return toDTO(updated);
    }

    @Override
    public void deletePhoneNumber(int id) {
        phoneRepository.deleteById(id);
    }

    private CustomerPhoneNumberDTO toDTO(CustomerPhoneNumber phone) {
        CustomerPhoneNumberDTO dto = new CustomerPhoneNumberDTO();
        dto.setCustomerPhoneNumberId(phone.getCustomerPhoneNumberId());
        dto.setCustomerPhoneNumber(phone.getCustomerPhoneNumber());
        dto.setIsActive(phone.isActive());
        dto.setCustomerId(phone.getCustomer() != null ? phone.getCustomer().getCustomerId() : null);
        return dto;
    }
}
