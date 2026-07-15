package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.PurchaseOrderItem;

public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Integer>  {
    
}
