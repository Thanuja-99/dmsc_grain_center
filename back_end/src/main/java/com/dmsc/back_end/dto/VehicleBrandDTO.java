package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleBrandDTO {

    private Integer vehicleBrandId;

    private String vehicleBrandName;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // Many-to-many
    private List<Integer> vehicleModelIds;
    private List<String> vehicleModelNames;
}