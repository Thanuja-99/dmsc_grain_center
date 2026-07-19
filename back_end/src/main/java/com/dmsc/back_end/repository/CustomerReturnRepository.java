package com.dmsc.back_end.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmsc.back_end.entity.CustomerReturn;

public interface CustomerReturnRepository extends JpaRepository<CustomerReturn, Integer> {

    List<CustomerReturn> findByCustomerOrder_CustomerOrderId(Integer customerOrderId);

    List<CustomerReturn> findByItem_ItemNameContainingIgnoreCase(String itemName);

    List<CustomerReturn> findByReturnDate(LocalDate returnDate);

}