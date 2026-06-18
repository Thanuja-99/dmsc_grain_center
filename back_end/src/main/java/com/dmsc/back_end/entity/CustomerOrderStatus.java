package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer_order_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private Integer statusId;

    @Column(name = "status_name")
    private String statusName;

   @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "entered_by")
    private String enteredBy;

    @Column(name ="entered_date")
    private LocalDate enteredDate;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "update_date")
    private LocalDate updateDate;

    @OneToMany(mappedBy = "status")
    private List<CustomerOrder> orders;
}
