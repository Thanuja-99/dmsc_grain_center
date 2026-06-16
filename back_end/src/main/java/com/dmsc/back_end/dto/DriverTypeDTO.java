package com.dmsc.back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverTypeDTO {
    
    private int driverTypeId;
    private String driverTypeName;
}
