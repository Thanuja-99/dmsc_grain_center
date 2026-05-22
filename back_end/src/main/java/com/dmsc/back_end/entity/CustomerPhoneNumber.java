package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
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

}