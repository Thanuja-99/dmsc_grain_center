package com.dmsc.back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.Billing;

public interface BillingRepository extends JpaRepository<Billing, Integer> {

    List<Billing> findByCustomer_CallingNameContainingIgnoreCase(String customerName);

    List<Billing> findByBillId(int billId);

    List<Billing> findByBillDate(LocalDate billDate);

    List<Billing> findByCustomer_CustomerId(int customerId);

}