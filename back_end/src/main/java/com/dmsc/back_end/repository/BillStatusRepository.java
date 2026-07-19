package com.dmsc.back_end.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.BillStatus;

public interface BillStatusRepository extends JpaRepository<BillStatus, Integer> {

    List<BillStatus> findByBillStatusNameContainingIgnoreCase(String name);

}