package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Customer;


public interface CustomerRepository 
    extends JpaRepository<Customer, Integer> {

    List<Customer> findByCallingNameContainingIgnoreCase(String name);
        
    }


