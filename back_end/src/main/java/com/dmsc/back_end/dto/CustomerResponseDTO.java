package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;


@Data
public class CustomerResponseDTO {

    //Backend → Frontend (GET / SHOW)

    private int customerId;
    private String callingName;
    private LocalDate cusBd;
    private String customerNote;

    private String customerTypeName;  // NOT ID
    private String genderName;        // NOT ID

    private List<String> phoneNumbers;
}
