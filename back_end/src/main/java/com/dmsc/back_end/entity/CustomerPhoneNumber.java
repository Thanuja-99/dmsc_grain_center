package com.dmsc.back_end.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
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
    @Column(name="customer_phone_number_id")
    private int customerPhoneNumberId;

    @Column(name="customer_contact_number")
    private String customerPhoneNumber;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="entered_by")
    private String enteredBy;

    @Column(name="entered_date")
    private LocalDate enteredDate;

    @Column(name="update_by")
    private String updateBy;
    
    @Column(name="update_date")
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name="Customer_customer_id")
    @JsonIgnore
    private Customer customer;

}