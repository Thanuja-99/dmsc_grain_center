package com.dmsc.back_end.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_status_id")
    private int billStatusId;

    @Column(name = "bill_status_name")
    private String billStatusName;

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

    @OneToMany(mappedBy = "billStatus", cascade = CascadeType.ALL)
    private List<Billing> billings = new ArrayList<>();

}