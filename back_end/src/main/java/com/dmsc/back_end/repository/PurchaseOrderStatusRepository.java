package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.PurchaseOrderStatus;

public interface PurchaseOrderStatusRepository extends JpaRepository<PurchaseOrderStatus, Integer> {
    
}
