package com.dmsc.back_end.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_type_id")
    private int supplierTypeId;

    @Column(name = "supplier_type_name")
    private String supplierTypeName;
}
