package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderItemDTO {

    private Integer customerOrderItemId;
    private Integer qty;
    private Double unitPrice;

    private boolean isActive;

    private String enteredBy;
    private LocalDate enteredDate;

    private String updateBy;
    private LocalDate updateDate;

    private Integer orderId;
    private Integer itemId;
    private Integer storeId;
}