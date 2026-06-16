package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverPhoneNumberDTO {


    private Integer driverPhoneNumberId;
    private String driverPhoneNumber;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;
    private Integer driverId;
}
