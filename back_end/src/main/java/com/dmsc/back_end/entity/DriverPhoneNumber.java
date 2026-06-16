package com.dmsc.back_end.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "driver_phone_number")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverPhoneNumber {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="driver_phone_number_id")
    private int driverPhoneNumberId;

    @Column(name="driver_phone_number",length = 10)
    private String driverPhoneNumber;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name="entered_by")
    private String enteredBy;

    @Column(name="entered_date")
    private LocalDate enteredDate;

    @Column(name="update_by")
    private String updateBy;
    
    @Column(name="update_date")
    private LocalDate updateDate;

    @ManyToOne
    @JoinColumn(name="Driver_driver_id")
    private Driver driver;
}
