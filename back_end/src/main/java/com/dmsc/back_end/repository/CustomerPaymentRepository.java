package com.dmsc.back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerPayment;

public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Integer> {

    List<CustomerPayment> findByBilling_BillId(Integer billId);

    List<CustomerPayment> findByPaymentDate(LocalDate paymentDate);

}