package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name="customer_phone_number")
public class CustomerPhoneNumber{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerPhoneNumberId;
    private int customerPhonetNumber;
    private boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name="Customer_customer_id")
    private Customer customer;

}