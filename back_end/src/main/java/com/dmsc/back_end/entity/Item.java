package com.dmsc.back_end.entity;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="item")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "Item_volume")
    private String itemVolume;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    @Column(name = "sales_price")
    private Double salesPrice;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "Manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "expire_date")
    private LocalDate expireDate;

    @Column(name = "item_note")
    private String itemNote;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "entered_date")
    private LocalDate enteredDate;

    @Column(name = "update_by")
    private String updateBy;
    
    @Column(name = "update_date")
    private LocalDate updateDate;



    @ManyToOne
    @JoinColumn(name = "Supplier_supplier_id")
    private Supplier supplier;

    @ManyToMany
    @JoinTable(
        name = "item_has_item_brand",
        joinColumns = @JoinColumn(name = "Item_item_id"),
        inverseJoinColumns = @JoinColumn(name = "Item_Brand_item_brand_id")
    )
    private List<ItemBrand> itemBrands = new ArrayList<>();

}
