package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {

    private Integer purchaseOrderId;
    private LocalDate orderDate;
    private String purchaseOrderNote;

    private boolean isActive;

    private String enteredBy;
    private LocalDate enteredDate;

    private String updateBy;
    private LocalDate updateDate;

    private Integer supplierId;
    private Integer statusId;
    private Integer storeId;
}
