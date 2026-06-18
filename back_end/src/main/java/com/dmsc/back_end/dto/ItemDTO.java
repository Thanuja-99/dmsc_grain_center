package com.dmsc.back_end.dto;

import java.time.LocalDate;
import java.util.List;

import com.dmsc.back_end.entity.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private Integer itemId;

    private String itemName;

    private String itemVolume;

    private Double purchasePrice;

    private Double salesPrice;

    private Integer quantity;

    private LocalDate manufacturingDate;

    private LocalDate expireDate;

    private String itemNote;

    private boolean isActive;

    private String enteredBy;

    private LocalDate enteredDate;

    private String updateBy;

    private LocalDate updateDate;

    // FK (Supplier)
    private Integer supplierId;
    private String supplierName;

    // Many-to-Many (ItemBrand)
    private List<Integer> itemBrandIds;
    private List<String> itemBrandNames;
}
