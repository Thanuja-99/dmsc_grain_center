package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrnDTO {

    private Integer grnId;
    private LocalDate grnDate;
    private String grnNote;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;

    private Integer grnStatusId;
    private String grnStatusName;

    private Integer storeId;
    private String storeName;

    private Integer purchaseOrderId;

    private Integer supplierId;
    private String supplierName;


    private List<GrnItemDTO> grnItems = new ArrayList<>();

}