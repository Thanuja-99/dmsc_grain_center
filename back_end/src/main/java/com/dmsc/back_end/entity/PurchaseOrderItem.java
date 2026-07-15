package com.dmsc.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_item_id")
    private int purchaseItemId;

    @Column(name = "quntity")
    private Integer quantity;

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
    @JoinColumn(name = "Purchase_Order_purchase_order_id")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "Item_item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "Item_Supplier_supplier_id")
    private Supplier supplier;
}