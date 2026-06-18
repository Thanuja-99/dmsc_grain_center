package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerOrder;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

    
} 
