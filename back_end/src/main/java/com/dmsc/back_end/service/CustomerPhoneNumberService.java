package com.dmsc.back_end.service;

import java.util.List;

import com.dmsc.back_end.dto.CustomerPhoneNumberDTO;

public interface CustomerPhoneNumberService {
    
    List<CustomerPhoneNumberDTO> getAllPhoneNumbers();

    List<CustomerPhoneNumberDTO> getPhoneNumbersByCustomerId(int customerId);

    CustomerPhoneNumberDTO createPhoneNumber(CustomerPhoneNumberDTO dto);

    CustomerPhoneNumberDTO updatePhoneNumber(int id, CustomerPhoneNumberDTO dto);

    void deletePhoneNumber(int id);

}

