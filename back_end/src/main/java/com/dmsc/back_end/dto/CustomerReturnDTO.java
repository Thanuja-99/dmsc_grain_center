package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReturnDTO {

    private Integer customerReturnId;

    private LocalDate returnDate;

    private String returnReason;

    private Integer qty;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    private Integer customerOrderId;

    private Integer itemId;
    private String itemName;

}