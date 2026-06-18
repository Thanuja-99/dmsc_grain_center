package com.dmsc.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierTypeDTO {

    private Integer supplierTypeId;
    private String supplierTypeName;
}
