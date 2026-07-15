package com.dmsc.back_end.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "purchase_order_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_order_id")
    private int purchaseOrderId;

    @Column(name = "purchase_order_status")
    private String purchaseOrderStatus;

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
}