package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Customer;


public interface CustomerRepository 
    extends JpaRepository<Customer, Integer> {
        
    }


