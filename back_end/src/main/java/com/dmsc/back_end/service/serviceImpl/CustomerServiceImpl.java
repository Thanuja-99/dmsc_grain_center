package com.dmsc.back_end.service.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dmsc.back_end.dto.CustomerDTO;
import com.dmsc.back_end.entity.Customer;
import com.dmsc.back_end.entity.CustomerPhoneNumber;
import com.dmsc.back_end.entity.CustomerType;
import com.dmsc.back_end.entity.Gender;
import com.dmsc.back_end.repository.CustomerPhoneNumberRepository;
import com.dmsc.back_end.repository.CustomerRepository;
import com.dmsc.back_end.repository.CustomerTypeRepository;
import com.dmsc.back_end.repository.GenderRepository;
import com.dmsc.back_end.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerTypeRepository customerTypeRepository;

    @Autowired
    GenderRepository genderRepository;

    @Autowired
    CustomerPhoneNumberRepository phoneRepository;

    // ================= GET BY ID =================
    @Override
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return toDTO(customer);
    }


    @Override
    public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
}

    // ================= SEARCH (simple by ID or replace later) =================
    @Override
    public List<CustomerDTO> searchCustomers(int id) {
        return customerRepository.findById(id)
                .map(customer -> List.of(toDTO(customer)))
                .orElse(new ArrayList<>());
    }
    
    @Override
    public List<CustomerDTO> searchCustomers(String name) {
        return customerRepository.findByCallingNameContainingIgnoreCase(name).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ================= CREATE =================
    @Override
    @Transactional
    public CustomerDTO createCustomer(CustomerDTO dto) {

        CustomerType customerType = customerTypeRepository.findById(dto.getCustomerTypeId())
                .orElseThrow(() -> new RuntimeException("Customer Type not found"));

        Gender gender = genderRepository.findById(dto.getGenderId())
                .orElseThrow(() -> new RuntimeException("Gender not found"));

        Customer customer = new Customer();
        customer.setCallingName(dto.getCallingName());
        customer.setCusBd(dto.getCusBd());
        customer.setCustomerNote(dto.getCustomerNote());
        customer.setActive(true);
        customer.setEnteredBy(dto.getEnteredBy());
        customer.setEnteredDate(LocalDate.now());
        customer.setCustomerType(customerType);
        customer.setGender(gender);
        customer.setPhoneNumbers(new ArrayList<>());

        Customer savedCustomer = customerRepository.save(customer);

        // save phone numbers
        if (dto.getPhoneNumbers() != null) {
            for (String phoneNum : dto.getPhoneNumbers()) {
                CustomerPhoneNumber phone = new CustomerPhoneNumber();
                phone.setCustomerPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setCustomer(savedCustomer);
                phoneRepository.save(phone);
            }
        }

        return toDTO(savedCustomer);
    }

    // ================= UPDATE =================
    @Override
    @Transactional
    public CustomerDTO updateCustomer(int id, CustomerDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if (dto.getCustomerTypeId() != null) {
            CustomerType customerType = customerTypeRepository.findById(dto.getCustomerTypeId())
                    .orElseThrow(() -> new RuntimeException("Customer Type not found"));
            customer.setCustomerType(customerType);
        }

        if (dto.getGenderId() != null) {
            Gender gender = genderRepository.findById(dto.getGenderId())
                    .orElseThrow(() -> new RuntimeException("Gender not found"));
            customer.setGender(gender);
        }

        customer.setCallingName(dto.getCallingName());
        customer.setCusBd(dto.getCusBd());
        customer.setCustomerNote(dto.getCustomerNote());

        if (dto.getIsActive() != null) {
            customer.setActive(dto.getIsActive());
        }

        customer.setUpdateBy(dto.getUpdateBy());
        customer.setUpdateDate(LocalDate.now());

        // remove old phones (simple way)
        phoneRepository.deleteByCustomer_CustomerId(id);

        // add new phones
        if (dto.getPhoneNumbers() != null) {
            for (String phoneNum : dto.getPhoneNumbers()) {
                CustomerPhoneNumber phone = new CustomerPhoneNumber();
                phone.setCustomerPhoneNumber(phoneNum);
                phone.setActive(true);
                phone.setEnteredDate(LocalDate.now());
                phone.setCustomer(customer);
                phoneRepository.save(phone);
            }
        }

        Customer updated = customerRepository.save(customer);
        return toDTO(updated);
    }

    // ================= DELETE (soft delete) =================
    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        customer.setActive(false);
        customerRepository.save(customer);
    }

    // ================= ENTITY → DTO =================
    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();

        dto.setCustomerId(customer.getCustomerId());
        dto.setCallingName(customer.getCallingName());
        dto.setCusBd(customer.getCusBd());
        dto.setCustomerNote(customer.getCustomerNote());
        dto.setIsActive(customer.isActive());
        dto.setEnteredBy(customer.getEnteredBy());
        dto.setEnteredDate(customer.getEnteredDate());
        dto.setUpdateBy(customer.getUpdateBy());
        dto.setUpdateDate(customer.getUpdateDate());

        if (customer.getCustomerType() != null) {
            dto.setCustomerTypeId(customer.getCustomerType().getCustomerTypeId());
            dto.setCustomerTypeName(customer.getCustomerType().getCustomerTypeName());
        }

        if (customer.getGender() != null) {
            dto.setGenderId(customer.getGender().getGenderId());
            dto.setGenderName(customer.getGender().getGenderName());
        }

        if (customer.getPhoneNumbers() != null) {
            dto.setPhoneNumbers(
                    customer.getPhoneNumbers()
                            .stream()
                            .map(CustomerPhoneNumber::getCustomerPhoneNumber)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
}