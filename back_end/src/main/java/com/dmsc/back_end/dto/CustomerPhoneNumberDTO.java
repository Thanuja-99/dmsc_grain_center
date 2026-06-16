package com.dmsc.back_end.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPhoneNumberDTO {

    private Integer customerPhoneNumberId;
    private String customerPhoneNumber;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;
    private Integer customerId;
}
