package com.dmsc.back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.SupplierReturn;

public interface SupplierReturnRepository extends JpaRepository<SupplierReturn, Integer> {

    List<SupplierReturn> findByGrn_GrnId(Integer grnId);

    List<SupplierReturn> findByItem_ItemNameContainingIgnoreCase(String itemName);

    List<SupplierReturn> findByReturnDate(LocalDate returnDate);

}