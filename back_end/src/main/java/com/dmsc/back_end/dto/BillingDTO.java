package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingDTO {

    private Integer billId;

    // Runtime generated (Not stored in DB)
    private String billNo;

    private LocalDate billDate;

    private Double totalAmount;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    private Integer customerOrderId;

    private Integer customerId;
    private String customerName;

    private Integer billStatusId;
    private String billStatusName;

}