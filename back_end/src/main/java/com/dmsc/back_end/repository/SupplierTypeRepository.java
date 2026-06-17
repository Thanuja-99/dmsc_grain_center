package com.dmsc.back_end.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.SupplierType;

public interface SupplierTypeRepository extends JpaRepository<SupplierType, Integer> {
}
