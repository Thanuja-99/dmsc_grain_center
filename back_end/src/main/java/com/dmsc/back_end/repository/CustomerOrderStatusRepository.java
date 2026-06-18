package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerOrderStatus;

public interface CustomerOrderStatusRepository extends JpaRepository<CustomerOrderStatus, Integer> {
    
}
