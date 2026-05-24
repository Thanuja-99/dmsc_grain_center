package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class CustomerRequestDTO {

     //Frontend → Backend (SAVE / UPDATE)

        private String callingName;
        private LocalDate cusBd;
        private String customerNote;

        private int customerTypeId;  //Only Id
        private int genderId;   //Only Id

        private List<String> phoneNumbers;
}
