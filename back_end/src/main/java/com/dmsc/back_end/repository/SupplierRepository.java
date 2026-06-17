package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

    List<Supplier> findBySupplierNameContainingIgnoreCase(String name);


}
