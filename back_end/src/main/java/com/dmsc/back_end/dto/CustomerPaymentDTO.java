package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPaymentDTO {

    private Integer customerPaymentId;

    private Double amount;

    private LocalDate paymentDate;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // Billing
    private Integer billId;

    // Runtime only (Not stored in database)
    private String billNo;

    private Integer customerId;

    private String customerName;

}