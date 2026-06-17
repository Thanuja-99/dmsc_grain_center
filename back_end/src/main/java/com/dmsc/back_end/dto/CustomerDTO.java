package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {



    private Integer customerId;
    private String callingName;
    private LocalDate cusBd;
    private String customerNote;
    private Boolean isActive;
    private String enteredBy;
    private LocalDate enteredDate;
    private String updateBy;
    private LocalDate updateDate;
    
    private Integer customerTypeId;
    private Integer genderId;
    
    private String customerTypeName;
    private String genderName;
    
    private List<String> phoneNumbers;
}
