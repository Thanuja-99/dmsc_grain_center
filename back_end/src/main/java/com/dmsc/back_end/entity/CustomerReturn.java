package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_return")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerReturn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_return_id")
    private Integer customerReturnId;

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
    @JoinColumn(name = "Customer_Order_customer_order_id")
    private CustomerOrder customerOrder;

    @ManyToOne
    @JoinColumn(name = "item_item_id")
    private Item item;

}