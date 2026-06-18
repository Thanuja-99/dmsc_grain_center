package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    private Integer storeId;
    private String storeName;
    private String storeAddress;
    private String storeNote;
    private boolean isActive;

    private String enteredBy;
    private LocalDate enteredDate;

    private String updateBy;
    private LocalDate updateDate;
}