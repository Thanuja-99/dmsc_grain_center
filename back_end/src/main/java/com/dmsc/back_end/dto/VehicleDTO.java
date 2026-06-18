package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {

    private Integer vehicleId;

    private String vehicleNumberPlate;

    private Double capacity;

    private String vehicleNote;

    private String vehicleType;

    private String vehicleModel;

    private Boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // FK
    private Integer vehicleTypeId;
    private String vehicleTypeName;

    private Integer vehicleModelId;
    private String vehicleModelName;
}
