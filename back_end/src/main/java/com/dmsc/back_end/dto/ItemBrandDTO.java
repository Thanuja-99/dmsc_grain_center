package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemBrandDTO {
    private Integer itemBrandId;

    private String itemBrandName;

    private boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // Many-to-Many mapping
    private List<Integer> itemIds;
    private List<String> itemNames;
}
