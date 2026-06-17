package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_address")
    private String supplierAddress;

    @Column(name = "supplier_note")
    private String supplierNote;

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
    @JoinColumn(name = "Supplier_Type_supplier_type_id")
    private SupplierType supplierType;

    @ManyToOne
    @JoinColumn(name = "Gender_gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<SupplierPhoneNumber> phoneNumbers = new ArrayList<>();
}
