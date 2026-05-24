package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerPhoneNumber;

public interface CustomerPhoneNumberRepository 
        extends JpaRepository<CustomerPhoneNumber, Integer> {

}