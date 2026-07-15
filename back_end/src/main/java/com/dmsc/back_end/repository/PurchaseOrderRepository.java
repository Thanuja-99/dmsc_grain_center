package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    
}
