package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierPaymentDTO {

    private Integer supplierPaymentId;

    private Double amount;

    private LocalDate paymentDate;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // GRN
    private Integer grnId;

    // Runtime Only
    private Integer supplierId;

    private String supplierName;

}