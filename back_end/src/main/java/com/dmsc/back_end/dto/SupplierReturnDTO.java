package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierReturnDTO {

    private Integer supplierReturnId;

    private LocalDate returnDate;

    private String returnReason;

    private Integer qty;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    private Integer grnId;

    private Integer itemId;
    private String itemName;

}