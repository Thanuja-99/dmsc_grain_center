package com.dmsc.back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.SupplierPayment;

public interface SupplierPaymentRepository extends JpaRepository<SupplierPayment, Integer> {

    List<SupplierPayment> findByGrn_GrnId(Integer grnId);

    List<SupplierPayment> findByPaymentDate(LocalDate paymentDate);

}