package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    
    private Integer driverId;
    private String driverCallingName;
    private LocalDate driverBd;
    private String driverNote;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;

    private Integer driverTypeId;
    private String driverTypeName;

    private Integer genderId;
    private String genderName;

    private List<String> phoneNumbers;

}
