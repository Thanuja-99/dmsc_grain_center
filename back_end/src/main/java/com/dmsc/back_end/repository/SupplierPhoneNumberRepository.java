package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.SupplierPhoneNumber;

public interface SupplierPhoneNumberRepository extends JpaRepository<SupplierPhoneNumber, Integer> {
    
    List<SupplierPhoneNumber> findBySupplier_SupplierId(Integer supplierId);
    void deleteBySupplier_SupplierId(Integer supplierId);

} 