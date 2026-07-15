package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderStatusDTO {

    private Integer purchaseOrderId;
    private String purchaseOrderStatus;

    private boolean isActive;

    private String enteredBy;
    private LocalDate enteredDate;

    private String updateBy;
    private LocalDate updateDate;
}