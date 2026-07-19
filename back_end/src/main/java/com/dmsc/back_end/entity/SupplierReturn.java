package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "supplier_return")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_return_id")
    private Integer supplierReturnId;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "return_reason")
    private String returnReason;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name = "entered_date")
    private LocalDate enteredDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name = "GRN_grn_id")
    private Grn grn;

    @ManyToOne
    @JoinColumn(name = "item_item_id")
    private Item item;

}