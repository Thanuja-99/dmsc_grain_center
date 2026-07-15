package com.dmsc.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private int purchaseOrderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "purchase_order_note")
    private String purchaseOrderNote;

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

    @ManyToOne
    @JoinColumn(name = "Purchase_Orde_Status_purchase_order_id")
    private PurchaseOrderStatus status;

    @ManyToOne
    @JoinColumn(name = "Store_store_id")
    private Store store;
}