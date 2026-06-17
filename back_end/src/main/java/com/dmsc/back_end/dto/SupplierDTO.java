package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDTO {
    private Integer supplierId;
    private String supplierName;
    private String supplierAddress;
    private String supplierNote;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;
    
    private Integer supplierTypeId;
    private Integer genderId;
    
    private String supplierTypeName;
    private String genderName;
    
    private List<String> phoneNumbers;
}
