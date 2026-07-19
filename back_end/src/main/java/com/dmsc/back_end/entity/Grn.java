package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "grn")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grn_id")
    private int grnId;

    @Column(name = "grn_date")
    private LocalDate grnDate;

    @Column(name = "grn_note")
    private String grnNote;

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
    @JoinColumn(name = "GRN_Status_grn_status_id")
    private GrnStatus grnStatus;

    @ManyToOne
    @JoinColumn(name = "Store_store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "purchase_order_purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "supplier_supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "grn", cascade = CascadeType.ALL) 
    private List<GrnItem> grnItems = new ArrayList<>();

}