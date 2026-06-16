package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerPhoneNumber;

public interface CustomerPhoneNumberRepository extends JpaRepository<CustomerPhoneNumber, Integer> {
    
    
    List<CustomerPhoneNumber> findByCustomer_CustomerId(Integer customerId);
    void deleteByCustomer_CustomerId(Integer customerId);

}