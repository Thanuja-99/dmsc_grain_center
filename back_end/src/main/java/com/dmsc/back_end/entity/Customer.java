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
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "calling_name")
    private String callingName;

    @Column(name = "cus_bd")
    private LocalDate cusBd;

    @Column(name = "customer_note")
    private String customerNote;

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
    @JoinColumn(name = "Customer_Type_customer_type_id")
    private CustomerType customerType;

    @ManyToOne
    @JoinColumn(name = "Gender_gender_id")
    private Gender gender;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<CustomerPhoneNumber> phoneNumbers = new ArrayList<>();

}